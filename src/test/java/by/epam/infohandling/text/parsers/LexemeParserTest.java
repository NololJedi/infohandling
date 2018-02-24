package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.Symbol;
import by.epam.infohandling.text.composite.SymbolType;
import by.epam.infohandling.text.composite.TextComposite;
import by.epam.infohandling.text.composite.lexeme.Lexeme;
import by.epam.infohandling.text.composite.lexeme.LexemeType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LexemeParserTest {

    private static final String ARTICLE_CONTENT_EXAMPLE = "a";
    private static final String WORD_CONTENT_EXAMPLE = "Dog";
    private static final String MATH_EXPRESSION_EXAMPLE = "3*3+(3*3)";

    private static Lexeme article;
    private static Lexeme word;
    private static Lexeme mathExpression;

    private static LexemeParser lexemeParser;

    @BeforeClass
    public static void setWordParser() {
        lexemeParser = LexemeParser.getInstance();
    }

    @BeforeClass
    public static void setTestingObjects() {
        Symbol symbol = new Symbol("a", SymbolType.ALPHABET);
        article = new Lexeme();
        article.setLexemeType(LexemeType.WORD);
        article.addTextComponent(symbol);

        Symbol firstLetter = new Symbol("D", SymbolType.ALPHABET);
        Symbol secondLetter = new Symbol("o", SymbolType.ALPHABET);
        Symbol thirdLetter = new Symbol("g", SymbolType.ALPHABET);

        word = new Lexeme();
        word.setLexemeType(LexemeType.WORD);
        word.addTextComponent(firstLetter);
        word.addTextComponent(secondLetter);
        word.addTextComponent(thirdLetter);

        Symbol number = new Symbol("3",SymbolType.NUMBER);
        Symbol plus = new Symbol("+",SymbolType.MATH);
        Symbol multiplication = new Symbol("*",SymbolType.MATH);
        Symbol openBracket = new Symbol("(",SymbolType.MATH);
        Symbol closeBracket = new Symbol(")",SymbolType.MATH);

        mathExpression = new Lexeme();
        mathExpression.setLexemeType(LexemeType.MATH_EXPRESSION);
        mathExpression.addTextComponent(number);
        mathExpression.addTextComponent(multiplication);
        mathExpression.addTextComponent(number);
        mathExpression.addTextComponent(plus);
        mathExpression.addTextComponent(openBracket);
        mathExpression.addTextComponent(number);
        mathExpression.addTextComponent(multiplication);
        mathExpression.addTextComponent(number);
        mathExpression.addTextComponent(closeBracket);
    }

    @Test
    public void shouldWordParsingBeSuccessful() {
        Lexeme actualWord = (Lexeme) lexemeParser.parseTextComponent(WORD_CONTENT_EXAMPLE);

        Assert.assertEquals(word, actualWord);
    }

    @Test
    public void shouldWordParsingFailed() {
        String incorrectWordContent = "dog.";
        Lexeme actualWord = (Lexeme) lexemeParser.parseTextComponent(incorrectWordContent);

        Assert.assertNotEquals(word, actualWord);
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
        Lexeme actualLexeme = (Lexeme) lexemeParser.parseTextComponent(MATH_EXPRESSION_EXAMPLE);

        Assert.assertEquals(mathExpression,actualLexeme);
    }

    @Test
    public void shouldMathExpressionParsingFailed(){
        String currentMathContent = "2+2*45";
        Lexeme actualLexeme = (Lexeme) lexemeParser.parseTextComponent(currentMathContent);

        Assert.assertNotEquals(mathExpression,actualLexeme);
    }
}
