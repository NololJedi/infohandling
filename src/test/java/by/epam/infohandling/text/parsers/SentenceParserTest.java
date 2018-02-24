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

public class SentenceParserTest {

    private static final String SENTENCE_CONTENT_EXAMPLE = "Dog eat a bone, cat 3*3+(3*3) milk.";

    private static final Symbol COMMA_SYMBOL = new Symbol(",", SymbolType.PUNCTUATION);
    private static final Symbol DOT_SYMBOL = new Symbol(".", SymbolType.PUNCTUATION);
    private static final Symbol SPACE_SYMBOL = new Symbol(" ", SymbolType.PUNCTUATION);

    private static SentenceParser sentenceParser;

    private static Lexeme wordDog;
    private static Lexeme wordEat;
    private static Lexeme article;
    private static Lexeme wordBone;
    private static Lexeme wordCat;
    private static Lexeme wordMilk;
    private static Lexeme mathExpression;

    @BeforeClass
    public static void setTestingObjects() {
        Symbol letterA = new Symbol("a", SymbolType.ALPHABET);
        Symbol letterD = new Symbol("D", SymbolType.ALPHABET);
        Symbol letterO = new Symbol("o", SymbolType.ALPHABET);
        Symbol letterG = new Symbol("g", SymbolType.ALPHABET);
        Symbol letterE = new Symbol("e", SymbolType.ALPHABET);
        Symbol letterT = new Symbol("t", SymbolType.ALPHABET);
        Symbol letterB = new Symbol("b", SymbolType.ALPHABET);
        Symbol letterN = new Symbol("n", SymbolType.ALPHABET);
        Symbol letterC = new Symbol("c", SymbolType.ALPHABET);
        Symbol letterM = new Symbol("m", SymbolType.ALPHABET);
        Symbol letterI = new Symbol("i", SymbolType.ALPHABET);
        Symbol letterL = new Symbol("l", SymbolType.ALPHABET);
        Symbol letterK = new Symbol("k", SymbolType.ALPHABET);

        Symbol number = new Symbol("3", SymbolType.NUMBER);
        Symbol plus = new Symbol("+", SymbolType.MATH);
        Symbol multiplication = new Symbol("*", SymbolType.MATH);
        Symbol openBracket = new Symbol("(", SymbolType.MATH);
        Symbol closeBracket = new Symbol(")", SymbolType.MATH);

        wordDog = new Lexeme();
        wordDog.setLexemeType(LexemeType.WORD);
        wordDog.addTextComponent(letterD);
        wordDog.addTextComponent(letterO);
        wordDog.addTextComponent(letterG);

        wordEat = new Lexeme();
        wordEat.setLexemeType(LexemeType.WORD);
        wordEat.addTextComponent(letterE);
        wordEat.addTextComponent(letterA);
        wordEat.addTextComponent(letterT);

        article = new Lexeme();
        article.setLexemeType(LexemeType.WORD);
        article.addTextComponent(letterA);

        wordBone = new Lexeme();
        wordBone.setLexemeType(LexemeType.WORD);
        wordBone.addTextComponent(letterB);
        wordBone.addTextComponent(letterO);
        wordBone.addTextComponent(letterN);
        wordBone.addTextComponent(letterE);

        wordCat = new Lexeme();
        wordCat.setLexemeType(LexemeType.WORD);
        wordCat.addTextComponent(letterC);
        wordCat.addTextComponent(letterA);
        wordCat.addTextComponent(letterT);

        wordMilk = new Lexeme();
        wordMilk.setLexemeType(LexemeType.WORD);
        wordMilk.addTextComponent(letterM);
        wordMilk.addTextComponent(letterI);
        wordMilk.addTextComponent(letterL);
        wordMilk.addTextComponent(letterK);

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

    @BeforeClass
    public static void setSentenceParser() {
        sentenceParser = SentenceParser.getInstance();
    }

    @Test
    public void shouldSentenceParsingBeSuccessful() {
        TextComposite actualSentence = (TextComposite) sentenceParser.parseTextComponent(SENTENCE_CONTENT_EXAMPLE);
        List<TextComponent> actualComponents = actualSentence.getTextComponents();

        Lexeme actualWordDog = (Lexeme) actualComponents.get(0);
        Symbol firstSpace = (Symbol) actualComponents.get(1);
        Lexeme actualWordEat = (Lexeme) actualComponents.get(2);
        Symbol secondSpace = (Symbol) actualComponents.get(3);
        Lexeme actualArticle = (Lexeme) actualComponents.get(4);
        Symbol thirdSpace = (Symbol) actualComponents.get(5);
        Lexeme actualWordBone = (Lexeme) actualComponents.get(6);
        Symbol actualComma = (Symbol) actualComponents.get(7);
        Symbol fourthSpace = (Symbol) actualComponents.get(8);
        Lexeme actualWordCat = (Lexeme) actualComponents.get(9);
        Symbol fifthSpace = (Symbol) actualComponents.get(10);
        Lexeme actualMathExpression = (Lexeme) actualComponents.get(11);
        Symbol sixthSpace = (Symbol) actualComponents.get(12);
        Lexeme actualWordMilk = (Lexeme) actualComponents.get(13);
        Symbol actualDot = (Symbol) actualComponents.get(14);

        Assert.assertEquals(wordDog, actualWordDog);
        Assert.assertEquals(SPACE_SYMBOL, firstSpace);
        Assert.assertEquals(wordEat, actualWordEat);
        Assert.assertEquals(SPACE_SYMBOL, secondSpace);
        Assert.assertEquals(article, actualArticle);
        Assert.assertEquals(SPACE_SYMBOL, thirdSpace);
        Assert.assertEquals(wordBone, actualWordBone);
        Assert.assertEquals(COMMA_SYMBOL, actualComma);
        Assert.assertEquals(SPACE_SYMBOL, fourthSpace);
        Assert.assertEquals(wordCat, actualWordCat);
        Assert.assertEquals(SPACE_SYMBOL, fifthSpace);
        Assert.assertEquals(mathExpression, actualMathExpression);
        Assert.assertEquals(SPACE_SYMBOL, sixthSpace);
        Assert.assertEquals(wordMilk, actualWordMilk);
        Assert.assertEquals(DOT_SYMBOL, actualDot);
    }

    @Test
    public void shouldSentenceParsingFailed() {
        String incorrectSentenceContent = "Cat ate a fake! dog 1/2-15-45 beer?";
        TextComposite actualSentence = (TextComposite) sentenceParser.parseTextComponent(incorrectSentenceContent);
        List<TextComponent> actualComponents = actualSentence.getTextComponents();

        Lexeme actualWordDog = (Lexeme) actualComponents.get(0);
        Symbol firstSpace = (Symbol) actualComponents.get(1);
        Lexeme actualWordEat = (Lexeme) actualComponents.get(2);
        Symbol secondSpace = (Symbol) actualComponents.get(3);
        Lexeme actualArticle = (Lexeme) actualComponents.get(4);
        Symbol thirdSpace = (Symbol) actualComponents.get(5);
        Lexeme actualWordBone = (Lexeme) actualComponents.get(6);
        Symbol actualComma = (Symbol) actualComponents.get(7);
        Symbol fourthSpace = (Symbol) actualComponents.get(8);
        Lexeme actualWordCat = (Lexeme) actualComponents.get(9);
        Symbol fifthSpace = (Symbol) actualComponents.get(10);
        Lexeme actualMathExpression = (Lexeme) actualComponents.get(11);
        Symbol sixthSpace = (Symbol) actualComponents.get(12);
        Lexeme actualWordMilk = (Lexeme) actualComponents.get(13);
        Symbol actualDot = (Symbol) actualComponents.get(14);

        Assert.assertNotEquals(wordDog, actualWordDog);
        Assert.assertEquals(SPACE_SYMBOL, firstSpace);
        Assert.assertNotEquals(wordEat, actualWordEat);
        Assert.assertEquals(SPACE_SYMBOL, secondSpace);
        Assert.assertEquals(article, actualArticle);
        Assert.assertEquals(SPACE_SYMBOL, thirdSpace);
        Assert.assertNotEquals(wordBone, actualWordBone);
        Assert.assertNotEquals(COMMA_SYMBOL, actualComma);
        Assert.assertEquals(SPACE_SYMBOL, fourthSpace);
        Assert.assertNotEquals(wordCat, actualWordCat);
        Assert.assertEquals(SPACE_SYMBOL, fifthSpace);
        Assert.assertNotEquals(mathExpression, actualMathExpression);
        Assert.assertEquals(SPACE_SYMBOL, sixthSpace);
        Assert.assertNotEquals(wordMilk, actualWordMilk);
        Assert.assertNotEquals(DOT_SYMBOL, actualDot);
    }
}
