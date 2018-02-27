package by.epam.infohandling.text.parsers;

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

        checkLexeme(actualWord, LexemeType.WORD, "Dog");
    }

    @Test
    public void shouldArticleParsingBeSuccessful() {
        Lexeme article = (Lexeme) lexemeParser.parseTextComponent(ARTICLE_CONTENT_EXAMPLE);

        checkLexeme(article, LexemeType.WORD, "a");
    }

    @Test
    public void shouldMathExpressionParsingBeSuccessful() {
        Lexeme actualMathExpression = (Lexeme) lexemeParser.parseTextComponent(MATH_EXPRESSION_EXAMPLE);

        checkLexeme(actualMathExpression, LexemeType.MATH_EXPRESSION, "3*3+(3*3)");
    }

    private void checkLexeme(Lexeme lexeme, LexemeType lexemeType, String content) {
        LexemeType actualType = lexeme.getLexemeType();
        String actualContent = lexeme.getContent();

        Assert.assertEquals(lexemeType, actualType);
        Assert.assertEquals(content, actualContent);
    }
}
