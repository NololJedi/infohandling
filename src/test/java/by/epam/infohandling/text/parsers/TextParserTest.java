package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;
import by.epam.infohandling.text.composite.lexeme.Lexeme;
import by.epam.infohandling.text.composite.lexeme.LexemeType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TextParserTest {

    private static final Symbol SPACE = new Symbol(" ", SymbolType.PUNCTUATION);
    private static final Symbol NEW_LINE = new Symbol("\n", SymbolType.PUNCTUATION);
    private static final Symbol TABULATION = new Symbol("\t", SymbolType.PUNCTUATION);

    private static final String TEXT_CONTENT_EXAMPLE = "    Dog eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.\n" +
            "\tDog eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.\n" +
            "\tDog eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.";

    private static TextParser textParser;

    private static TextComposite firstParagraph;
    private static TextComposite secondParagraph;
    private static TextComposite thirdParagraph;

    @BeforeClass
    public static void setTextParser() {
        textParser = TextParser.getInstance();
    }

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
        Symbol letterCUp = new Symbol("C", SymbolType.ALPHABET);
        Symbol letterM = new Symbol("m", SymbolType.ALPHABET);
        Symbol letterI = new Symbol("i", SymbolType.ALPHABET);
        Symbol letterL = new Symbol("l", SymbolType.ALPHABET);
        Symbol letterK = new Symbol("k", SymbolType.ALPHABET);

        Symbol dot = new Symbol(".", SymbolType.PUNCTUATION);

        Lexeme wordDog = new Lexeme();
        wordDog.setLexemeType(LexemeType.WORD);
        wordDog.addTextComponent(letterD);
        wordDog.addTextComponent(letterO);
        wordDog.addTextComponent(letterG);

        Lexeme wordEat = new Lexeme();
        wordEat.setLexemeType(LexemeType.WORD);
        wordEat.addTextComponent(letterE);
        wordEat.addTextComponent(letterA);
        wordEat.addTextComponent(letterT);

        Lexeme article = new Lexeme();
        article.setLexemeType(LexemeType.WORD);
        article.addTextComponent(letterA);

        Lexeme wordBone = new Lexeme();
        wordBone.setLexemeType(LexemeType.WORD);
        wordBone.addTextComponent(letterB);
        wordBone.addTextComponent(letterO);
        wordBone.addTextComponent(letterN);
        wordBone.addTextComponent(letterE);

        Lexeme wordCat = new Lexeme();
        wordCat.setLexemeType(LexemeType.WORD);
        wordCat.addTextComponent(letterCUp);
        wordCat.addTextComponent(letterA);
        wordCat.addTextComponent(letterT);

        Lexeme wordCatLow = new Lexeme();
        wordCatLow.setLexemeType(LexemeType.WORD);
        wordCatLow.addTextComponent(letterC);
        wordCatLow.addTextComponent(letterA);
        wordCatLow.addTextComponent(letterT);

        Lexeme wordLick = new Lexeme();
        wordLick.setLexemeType(LexemeType.WORD);
        wordLick.addTextComponent(letterL);
        wordLick.addTextComponent(letterI);
        wordLick.addTextComponent(letterC);
        wordLick.addTextComponent(letterK);

        Lexeme wordMilk = new Lexeme();
        wordMilk.setLexemeType(LexemeType.WORD);
        wordMilk.addTextComponent(letterM);
        wordMilk.addTextComponent(letterI);
        wordMilk.addTextComponent(letterL);
        wordMilk.addTextComponent(letterK);

        TextComposite firstSentence = new TextComposite();
        firstSentence.setComponentType(ComponentType.SENTENCE);
        firstSentence.addTextComponent(wordDog);
        firstSentence.addTextComponent(SPACE);
        firstSentence.addTextComponent(wordEat);
        firstSentence.addTextComponent(SPACE);
        firstSentence.addTextComponent(article);
        firstSentence.addTextComponent(SPACE);
        firstSentence.addTextComponent(wordBone);
        firstSentence.addTextComponent(dot);

        TextComposite secondSentence = new TextComposite();
        secondSentence.setComponentType(ComponentType.SENTENCE);
        secondSentence.addTextComponent(wordCat);
        secondSentence.addTextComponent(SPACE);
        secondSentence.addTextComponent(wordLick);
        secondSentence.addTextComponent(SPACE);
        secondSentence.addTextComponent(wordMilk);
        secondSentence.addTextComponent(dot);

        TextComposite thirdSentence = new TextComposite();
        thirdSentence.setComponentType(ComponentType.SENTENCE);
        thirdSentence.addTextComponent(wordDog);
        thirdSentence.addTextComponent(SPACE);
        thirdSentence.addTextComponent(wordEat);
        thirdSentence.addTextComponent(SPACE);
        thirdSentence.addTextComponent(wordMilk);
        thirdSentence.addTextComponent(SPACE);
        thirdSentence.addTextComponent(wordCatLow);
        thirdSentence.addTextComponent(dot);

        TextComposite fourthSentence = new TextComposite();
        fourthSentence.setComponentType(ComponentType.SENTENCE);
        fourthSentence.addTextComponent(wordCat);
        fourthSentence.addTextComponent(SPACE);
        fourthSentence.addTextComponent(wordLick);
        fourthSentence.addTextComponent(SPACE);
        fourthSentence.addTextComponent(article);
        fourthSentence.addTextComponent(SPACE);
        fourthSentence.addTextComponent(wordBone);
        fourthSentence.addTextComponent(dot);

        firstParagraph = new TextComposite();
        firstParagraph.setComponentType(ComponentType.PARAGRAPH);
        firstParagraph.addTextComponent(firstSentence);
        firstParagraph.addTextComponent(SPACE);
        firstParagraph.addTextComponent(secondSentence);
        firstParagraph.addTextComponent(SPACE);
        firstParagraph.addTextComponent(thirdSentence);
        firstParagraph.addTextComponent(SPACE);
        firstParagraph.addTextComponent(fourthSentence);

        secondParagraph = new TextComposite();
        secondParagraph.setComponentType(ComponentType.PARAGRAPH);
        secondParagraph.addTextComponent(firstSentence);
        secondParagraph.addTextComponent(SPACE);
        secondParagraph.addTextComponent(secondSentence);
        secondParagraph.addTextComponent(SPACE);
        secondParagraph.addTextComponent(thirdSentence);
        secondParagraph.addTextComponent(SPACE);
        secondParagraph.addTextComponent(fourthSentence);

        thirdParagraph = new TextComposite();
        thirdParagraph.setComponentType(ComponentType.PARAGRAPH);
        thirdParagraph.addTextComponent(firstSentence);
        thirdParagraph.addTextComponent(SPACE);
        thirdParagraph.addTextComponent(secondSentence);
        thirdParagraph.addTextComponent(SPACE);
        thirdParagraph.addTextComponent(thirdSentence);
        thirdParagraph.addTextComponent(SPACE);
        thirdParagraph.addTextComponent(fourthSentence);
    }

    @Test
    public void shouldTextParsingBeSuccessful() {
        TextComposite actualText = (TextComposite) textParser.parseTextComponent(TEXT_CONTENT_EXAMPLE);
        List<TextComponent> actualParagraphs = actualText.getTextComponents();

        Symbol firstTabulation = (Symbol) actualParagraphs.get(0);
        TextComposite actualFirstParagraph = (TextComposite) actualParagraphs.get(1);
        Symbol firstNewLine = (Symbol) actualParagraphs.get(2);
        Symbol secondTabulation = (Symbol) actualParagraphs.get(3);
        TextComposite actualSecondParagraph = (TextComposite) actualParagraphs.get(4);
        Symbol secondNewLine = (Symbol) actualParagraphs.get(5);
        Symbol thirdTabulation = (Symbol) actualParagraphs.get(6);
        TextComposite actualThirdParagraph = (TextComposite) actualParagraphs.get(7);

        Assert.assertEquals(TABULATION, firstTabulation);
        Assert.assertEquals(firstParagraph, actualFirstParagraph);
        Assert.assertEquals(NEW_LINE, firstNewLine);
        Assert.assertEquals(TABULATION, secondTabulation);
        Assert.assertEquals(secondParagraph, actualSecondParagraph);
        Assert.assertEquals(NEW_LINE, secondNewLine);
        Assert.assertEquals(TABULATION, thirdTabulation);
        Assert.assertEquals(thirdParagraph, actualThirdParagraph);
    }

    @Test
    public void shouldTextParsingFailed() {
        String incorrectText = "    Dosg eaat i bonde. Cast licak mislk. Dodg eats mialk cast. dCat licdk t bosne.\n" +
                "\tDosg eaat i bonde. Cast licak mislk. Dodg eats mialk cast. dCat licdk t bosne.\n" +
                "\tDosg eaat i bonde. Cast licak mislk. Dodg eats mialk cast. dCat licdk t bosne.";

        TextComposite actualText = (TextComposite) textParser.parseTextComponent(incorrectText);
        List<TextComponent> actualParagraphs = actualText.getTextComponents();

        Symbol firstTabulation = (Symbol) actualParagraphs.get(0);
        TextComposite actualFirstParagraph = (TextComposite) actualParagraphs.get(1);
        Symbol firstNewLine = (Symbol) actualParagraphs.get(2);
        Symbol secondTabulation = (Symbol) actualParagraphs.get(3);
        TextComposite actualSecondParagraph = (TextComposite) actualParagraphs.get(4);
        Symbol secondNewLine = (Symbol) actualParagraphs.get(5);
        Symbol thirdTabulation = (Symbol) actualParagraphs.get(6);
        TextComposite actualThirdParagraph = (TextComposite) actualParagraphs.get(7);

        Assert.assertNotEquals(NEW_LINE, firstTabulation);
        Assert.assertNotEquals(firstParagraph, actualFirstParagraph);
        Assert.assertNotEquals(TABULATION, firstNewLine);
        Assert.assertNotEquals(NEW_LINE, secondTabulation);
        Assert.assertNotEquals(secondParagraph, actualSecondParagraph);
        Assert.assertNotEquals(TABULATION, secondNewLine);
        Assert.assertNotEquals(NEW_LINE, thirdTabulation);
        Assert.assertNotEquals(thirdParagraph, actualThirdParagraph);
    }

}
