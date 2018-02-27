package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.Symbol;
import by.epam.infohandling.text.composite.SymbolType;
import by.epam.infohandling.text.composite.lexeme.Lexeme;
import by.epam.infohandling.text.composite.lexeme.LexemeType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        Symbol firstLetter = mock(Symbol.class);
        when(firstLetter.getContent()).thenReturn("D");

        Symbol secondLetter = mock(Symbol.class);
        when(secondLetter.getContent()).thenReturn("o");

        Symbol thirdLetter = mock(Symbol.class);
        when(thirdLetter.getContent()).thenReturn("g");

        SymbolParser symbolParser = mock(SymbolParser.class);
        when(symbolParser.parseTextComponent("D")).thenReturn(firstLetter);
        when(symbolParser.parseTextComponent("o")).thenReturn(secondLetter);
        when(symbolParser.parseTextComponent("g")).thenReturn(thirdLetter);

        lexemeParser.setNextParser(symbolParser);
        Lexeme actualWord = (Lexeme) lexemeParser.parseTextComponent(WORD_CONTENT_EXAMPLE);

        checkLexeme(actualWord, LexemeType.WORD, "Dog");
    }

    @Test
    public void shouldArticleParsingBeSuccessful() {
        Symbol symbol = mock(Symbol.class);
        when(symbol.getContent()).thenReturn("a");
        when(symbol.getSymbolType()).thenReturn(SymbolType.ALPHABET);

        SymbolParser symbolParser = mock(SymbolParser.class);
        when(symbolParser.parseTextComponent("a")).thenReturn(symbol);

        lexemeParser.setNextParser(symbolParser);
        Lexeme article = (Lexeme) lexemeParser.parseTextComponent(ARTICLE_CONTENT_EXAMPLE);

        checkLexeme(article, LexemeType.WORD, "a");
    }

    @Test
    public void shouldMathExpressionParsingBeSuccessful() {
        Symbol number = mock(Symbol.class);
        when(number.getContent()).thenReturn("3");
        Symbol plus = mock(Symbol.class);
        when(plus.getContent()).thenReturn("+");
        Symbol multiplication =  mock(Symbol.class);
        when(multiplication.getContent()).thenReturn("*");
        Symbol openBracket = mock(Symbol.class);
        when(openBracket.getContent()).thenReturn("(");
        Symbol closeBracket = mock(Symbol.class);
        when(closeBracket.getContent()).thenReturn(")");

        SymbolParser symbolParser = mock(SymbolParser.class);
        when(symbolParser.parseTextComponent("3")).thenReturn(number);
        when(symbolParser.parseTextComponent("+")).thenReturn(plus);
        when(symbolParser.parseTextComponent("*")).thenReturn(multiplication);
        when(symbolParser.parseTextComponent("(")).thenReturn(openBracket);
        when(symbolParser.parseTextComponent(")")).thenReturn(closeBracket);

        lexemeParser.setNextParser(symbolParser);
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
