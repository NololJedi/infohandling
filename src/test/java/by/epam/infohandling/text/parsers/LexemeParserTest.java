package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.Symbol;
import by.epam.infohandling.text.composite.SymbolType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.TextComposite;
import by.epam.infohandling.text.composite.lexeme.Lexeme;
import by.epam.infohandling.text.composite.lexeme.LexemeType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class LexemeParserTest {

    private static final String ARTICLE_CONTENT_EXAMPLE = "a";
    private static final String WORD_CONTENT_EXAMPLE = "Dog";
    private static final String MATH_EXPRESSION_EXAMPLE = "3*3+(3*3)";

    private static Lexeme article;
    private static LexemeParser lexemeParser;

    @BeforeClass
    public static void setWordParser() {
        lexemeParser = LexemeParser.getInstance();
    }

    @BeforeClass
    public static void setArticle() {
        Symbol symbol = new Symbol("a", SymbolType.ALPHABET);
        article = new Lexeme();
        article.setLexemeType(LexemeType.WORD);
        article.addTextComponent(symbol);
    }

    @Test
    public void shouldWordParsingBeSuccessful() {
        Lexeme actualWord = (Lexeme) lexemeParser.parseTextComponent(WORD_CONTENT_EXAMPLE);
        List<TextComponent> actualLetters = actualWord.getTextComponents();

        Symbol firstLetter = new Symbol("D", SymbolType.ALPHABET);
        Symbol secondLetter = new Symbol("o", SymbolType.ALPHABET);
        Symbol thirdLetter = new Symbol("g", SymbolType.ALPHABET);

        Symbol actualFirstLetter = (Symbol) actualLetters.get(0);
        Symbol actualSecondLetter = (Symbol) actualLetters.get(1);
        Symbol actualThirdLetter = (Symbol) actualLetters.get(2);

        LexemeType actualType = actualWord.getLexemeType();

        Assert.assertEquals(firstLetter,actualFirstLetter);
        Assert.assertEquals(secondLetter,actualSecondLetter);
        Assert.assertEquals(thirdLetter,actualThirdLetter);
        Assert.assertEquals(LexemeType.WORD,actualType);
    }

    @Test
    public void shouldWordParsingFailed() {
        String incorrectWordContent = "Cat";
        Lexeme actualWord = (Lexeme) lexemeParser.parseTextComponent(incorrectWordContent);
        List<TextComponent> actualLetters = actualWord.getTextComponents();

        Symbol firstLetter = new Symbol("D", SymbolType.ALPHABET);
        Symbol secondLetter = new Symbol("o", SymbolType.ALPHABET);
        Symbol thirdLetter = new Symbol("g", SymbolType.ALPHABET);

        Symbol actualFirstLetter = (Symbol) actualLetters.get(0);
        Symbol actualSecondLetter = (Symbol) actualLetters.get(1);
        Symbol actualThirdLetter = (Symbol) actualLetters.get(2);

        LexemeType actualType = actualWord.getLexemeType();

        Assert.assertNotEquals(firstLetter,actualFirstLetter);
        Assert.assertNotEquals(secondLetter,actualSecondLetter);
        Assert.assertNotEquals(thirdLetter,actualThirdLetter);
        Assert.assertNotEquals(LexemeType.MATH_EXPRESSION,actualType);
    }

    @Test
    public void shouldArticleParsingBeSuccessful() {
        Lexeme actualArticle = (Lexeme) lexemeParser.parseTextComponent(ARTICLE_CONTENT_EXAMPLE);

        Assert.assertEquals(article, actualArticle);
    }

    @Test(expected = ClassCastException.class)
    public void shouldArticleParsingFailed() {
        String incorrectArticleContent = ".";
        TextComposite actualArticle = (TextComposite) lexemeParser.parseTextComponent(incorrectArticleContent);
    }

    @Test
    public void shouldMathExpressionParsingBeSuccessful(){
        Lexeme actualMathExpression = (Lexeme) lexemeParser.parseTextComponent(MATH_EXPRESSION_EXAMPLE);
        List<TextComponent> actualSymbols = actualMathExpression.getTextComponents();

        Symbol number = new Symbol("3",SymbolType.NUMBER);
        Symbol plus = new Symbol("+",SymbolType.MATH);
        Symbol multiplication = new Symbol("*",SymbolType.MATH);
        Symbol openBracket = new Symbol("(",SymbolType.MATH);
        Symbol closeBracket = new Symbol(")",SymbolType.MATH);

        Symbol firstSymbol = (Symbol) actualSymbols.get(0);
        Symbol secondSymbol = (Symbol) actualSymbols.get(1);
        Symbol thirdSymbol = (Symbol) actualSymbols.get(2);
        Symbol fourthSymbol = (Symbol) actualSymbols.get(3);
        Symbol fifthSymbol = (Symbol) actualSymbols.get(4);
        Symbol sixthSymbol = (Symbol) actualSymbols.get(5);
        Symbol seventhSymbol = (Symbol) actualSymbols.get(6);
        Symbol eighthSymbol = (Symbol) actualSymbols.get(7);
        Symbol ninthSymbol = (Symbol) actualSymbols.get(8);

        LexemeType actualType = actualMathExpression.getLexemeType();

        Assert.assertEquals(number,firstSymbol);
        Assert.assertEquals(multiplication,secondSymbol);
        Assert.assertEquals(number,thirdSymbol);
        Assert.assertEquals(plus,fourthSymbol);
        Assert.assertEquals(openBracket,fifthSymbol);
        Assert.assertEquals(number,sixthSymbol);
        Assert.assertEquals(multiplication,seventhSymbol);
        Assert.assertEquals(number,eighthSymbol);
        Assert.assertEquals(closeBracket,ninthSymbol);

        Assert.assertEquals(LexemeType.MATH_EXPRESSION,actualType);
    }

    @Test
    public void shouldMathExpressionParsingFailed(){
        String currentMathContent = "1/2-15-45";
        Lexeme actualMathExpression = (Lexeme) lexemeParser.parseTextComponent(currentMathContent);
        List<TextComponent> actualSymbols = actualMathExpression.getTextComponents();

        Symbol number = new Symbol("3",SymbolType.NUMBER);
        Symbol plus = new Symbol("+",SymbolType.MATH);
        Symbol multiplication = new Symbol("*",SymbolType.MATH);
        Symbol openBracket = new Symbol("(",SymbolType.MATH);
        Symbol closeBracket = new Symbol(")",SymbolType.MATH);

        Symbol firstSymbol = (Symbol) actualSymbols.get(0);
        Symbol secondSymbol = (Symbol) actualSymbols.get(1);
        Symbol thirdSymbol = (Symbol) actualSymbols.get(2);
        Symbol fourthSymbol = (Symbol) actualSymbols.get(3);
        Symbol fifthSymbol = (Symbol) actualSymbols.get(4);
        Symbol sixthSymbol = (Symbol) actualSymbols.get(5);
        Symbol seventhSymbol = (Symbol) actualSymbols.get(6);
        Symbol eighthSymbol = (Symbol) actualSymbols.get(7);
        Symbol ninthSymbol = (Symbol) actualSymbols.get(8);

        LexemeType actualType = actualMathExpression.getLexemeType();

        Assert.assertNotEquals(number,firstSymbol);
        Assert.assertNotEquals(multiplication,secondSymbol);
        Assert.assertNotEquals(number,thirdSymbol);
        Assert.assertNotEquals(plus,fourthSymbol);
        Assert.assertNotEquals(openBracket,fifthSymbol);
        Assert.assertNotEquals(number,sixthSymbol);
        Assert.assertNotEquals(multiplication,seventhSymbol);
        Assert.assertNotEquals(number,eighthSymbol);
        Assert.assertNotEquals(closeBracket,ninthSymbol);

        Assert.assertNotEquals(LexemeType.WORD,actualType);
    }
}
