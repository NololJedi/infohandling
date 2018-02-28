package by.epam.infohandling;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.util.ContentDeterminant;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static by.epam.infohandling.text.composite.ComponentType.*;
import static by.epam.infohandling.util.ContentDeterminant.*;

@RunWith(DataProviderRunner.class)
public class ContentDeterminantTest {

    @DataProvider
    public static Object[][] correctValues() {
        String letter = "a";
        String number = "2";
        String punctuation = ".";
        String word = "correct";
        String mathExpression = "6+9*(3-4)";

        return new Object[][]{
                {letter, ALPHABET_PATTERN, true},
                {number, NUMBER_PATTERN, true},
                {punctuation, PUNCTUATION_PATTERN, true},
                {word, WORD_PATTERN, true},
                {mathExpression, MATH_PATTERN, true},
        };
    }

    @DataProvider
    public static Object[][] correctTypes() {
        String letter = "a";
        String number = "2";
        String word = "correct";
        String mathExpression = "6+9*(3-4)";

        return new Object[][]{
                {letter, ALPHABET_SYMBOL},
                {number, NUMBER},
                {word, WORD},
                {mathExpression, MATH_EXPRESSION}
        };
    }

    @DataProvider
    public static Object[][] exceptionValues() {
        String nullContent = null;
        String emptyContent = "";
        String nullPatter = null;
        String emptyPattern = "";

        String content = "A";

        return new Object[][]{
                {nullContent, ALPHABET_PATTERN},
                {emptyContent, ALPHABET_PATTERN},
                {content, nullPatter},
                {content, emptyPattern}
        };
    }

    @Test
    @UseDataProvider("correctValues")
    public void shouldMatchingBeSuccessful(String content, String pattern, boolean expectedResult) {
        boolean actualResult = ContentDeterminant.matchContent(content, pattern);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    @UseDataProvider("correctTypes")
    public void shouldTypeDeterminantBeSuccessful(String content, ComponentType expectedType) {
        ComponentType actualType = ContentDeterminant.determinantType(content);

        Assert.assertEquals(actualType, expectedType);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("exceptionValues")
    public void shouldMatchingCauseIllegalArgumentException(String content, String pattern) {
        ContentDeterminant.matchContent(content, pattern);
    }
}
