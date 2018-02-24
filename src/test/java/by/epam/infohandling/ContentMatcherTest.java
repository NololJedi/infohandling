package by.epam.infohandling;

import by.epam.infohandling.text.parsers.ContentMatcher;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static by.epam.infohandling.text.parsers.ContentMatcher.*;

@RunWith(DataProviderRunner.class)
public class ContentMatcherTest {

    @DataProvider
    public static Object[][] correctValues(){
        String letter = "a";
        String number = "2";
        String math = "*";
        String punctuation = ".";
        String word = "correct";
        String mathExpression = "6+9*(3-4)";

        return new Object[][]{
                {letter,SYMBOL_ALPHABET_PATTERN,true},
                {number,SYMBOL_NUMBER_PATTERN,true},
                {math,SYMBOL_MATH_PATTERN,true},
                {punctuation,SYMBOL_PUNCTUATION_PATTERN,true},
                {word,LEXEME_WORD_PATTERN,true},
                {mathExpression,LEXEME_WORD_PATTERN,false}
        };
    }

    @DataProvider
    public static Object[][] incorrectValues(){
        String letter = "2";
        String number = ",";
        String math = "s";
        String punctuation = "/";
        String mathExpression = "correct";
        String word = "6+9*(3-4)";

        return new Object[][]{
                {letter,SYMBOL_ALPHABET_PATTERN,true},
                {number,SYMBOL_ALPHABET_PATTERN,true},
                {math,SYMBOL_MATH_PATTERN,true},
                {punctuation,SYMBOL_PUNCTUATION_PATTERN,true},
                {word,LEXEME_WORD_PATTERN,true},
                {mathExpression,LEXEME_WORD_PATTERN,false}
        };
    }

    @DataProvider
    public static Object[][] exceptionValues(){
        String nullContent = null;
        String emptyContent = "";
        String nullPatter = null;
        String emptyPattern = "";

        String content = "A";

        return new Object[][]{
                {nullContent,SYMBOL_ALPHABET_PATTERN},
                {emptyContent,SYMBOL_ALPHABET_PATTERN},
                {content,nullPatter},
                {content,emptyPattern}
        };
    }

    @Test
    @UseDataProvider("correctValues")
    public void shouldMatchingBeSuccessful(String content, String pattern, boolean expectedResult){
        boolean actualResult = ContentMatcher.contentMatch(content,pattern);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    @UseDataProvider("incorrectValues")
    public void shouldMatchingFailed(String content, String pattern, boolean expectedResult){
        boolean actualResult = ContentMatcher.contentMatch(content,pattern);

        Assert.assertNotEquals(expectedResult,actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("exceptionValues")
    public void shouldMatchingCauseIllegalArgumentException(String content, String pattern){
        ContentMatcher.contentMatch(content,pattern);
    }
}
