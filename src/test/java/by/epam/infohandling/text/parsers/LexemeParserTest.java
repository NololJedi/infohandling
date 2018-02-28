package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.Lexeme;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LexemeParserTest {

    private static final String ARTICLE_CONTENT_EXAMPLE = "a";
    private static final String WORD_CONTENT_EXAMPLE = "Dog";
    private static final String MATH_EXPRESSION_EXAMPLE = "3*3+(3*3)";

    private static LexemeParser lexemeParser;

    @BeforeClass
    public static void setLexemeParser() {
        lexemeParser = new LexemeParser();
    }

    @Test
    public void shouldWordParsingBeSuccessful() {
        Lexeme actualWord = (Lexeme) lexemeParser.parseTextComponent(WORD_CONTENT_EXAMPLE);

        checkLexeme(actualWord, ComponentType.WORD, "Dog");
    }

    @Test
    public void shouldArticleParsingBeSuccessful() {
        Lexeme article = (Lexeme) lexemeParser.parseTextComponent(ARTICLE_CONTENT_EXAMPLE);

        checkLexeme(article, ComponentType.ALPHABET_SYMBOL, "a");
    }

    @Test
    public void shouldMathExpressionParsingBeSuccessful() {
        Lexeme actualMathExpression = (Lexeme) lexemeParser.parseTextComponent(MATH_EXPRESSION_EXAMPLE);

        checkLexeme(actualMathExpression, ComponentType.MATH_EXPRESSION, "3*3+(3*3)");
    }

    private void checkLexeme(Lexeme lexeme, ComponentType lexemeType, String content) {
        ComponentType actualType = lexeme.getComponentType();
        String actualContent = lexeme.getContent();

        Assert.assertEquals(lexemeType, actualType);
        Assert.assertEquals(content, actualContent);
    }
}
