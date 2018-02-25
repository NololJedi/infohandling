package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;
import by.epam.infohandling.text.composite.lexeme.Lexeme;
import by.epam.infohandling.text.composite.lexeme.LexemeType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class ParagraphParserTest {

    private static final String PARAGRAPH_CONTENT_EXAMPLE = "Dog eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.";
    private static final Symbol SPACE = new Symbol(" ", SymbolType.PUNCTUATION);

    private static ParagraphParser paragraphParser;

    private static TextComposite firstSentence;
    private static TextComposite secondSentence;
    private static TextComposite thirdSentence;
    private static TextComposite fourthSentence;

    @BeforeClass
    public static void setParagraphParser() {
        paragraphParser = ParagraphParser.getInstance();
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

        firstSentence = new TextComposite();
        firstSentence.setComponentType(ComponentType.SENTENCE);
        firstSentence.addTextComponent(wordDog);
        firstSentence.addTextComponent(SPACE);
        firstSentence.addTextComponent(wordEat);
        firstSentence.addTextComponent(SPACE);
        firstSentence.addTextComponent(article);
        firstSentence.addTextComponent(SPACE);
        firstSentence.addTextComponent(wordBone);
        firstSentence.addTextComponent(dot);

        secondSentence = new TextComposite();
        secondSentence.setComponentType(ComponentType.SENTENCE);
        secondSentence.addTextComponent(wordCat);
        secondSentence.addTextComponent(SPACE);
        secondSentence.addTextComponent(wordLick);
        secondSentence.addTextComponent(SPACE);
        secondSentence.addTextComponent(wordMilk);
        secondSentence.addTextComponent(dot);

        thirdSentence = new TextComposite();
        thirdSentence.setComponentType(ComponentType.SENTENCE);
        thirdSentence.addTextComponent(wordDog);
        thirdSentence.addTextComponent(SPACE);
        thirdSentence.addTextComponent(wordEat);
        thirdSentence.addTextComponent(SPACE);
        thirdSentence.addTextComponent(wordMilk);
        thirdSentence.addTextComponent(SPACE);
        thirdSentence.addTextComponent(wordCatLow);
        thirdSentence.addTextComponent(dot);

        fourthSentence = new TextComposite();
        fourthSentence.setComponentType(ComponentType.SENTENCE);
        fourthSentence.addTextComponent(wordCat);
        fourthSentence.addTextComponent(SPACE);
        fourthSentence.addTextComponent(wordLick);
        fourthSentence.addTextComponent(SPACE);
        fourthSentence.addTextComponent(article);
        fourthSentence.addTextComponent(SPACE);
        fourthSentence.addTextComponent(wordBone);
        fourthSentence.addTextComponent(dot);
    }

    @Test
    public void shouldParagraphParsingBeSuccessful() {
        TextComposite actualParagraph = (TextComposite) paragraphParser.parseTextComponent(PARAGRAPH_CONTENT_EXAMPLE);
        List<TextComponent> actualComponents = actualParagraph.getTextComponents();

        TextComposite actualFirstSentence = (TextComposite) actualComponents.get(0);
        Symbol firstSpace = (Symbol) actualComponents.get(1);
        TextComposite actualSecondSentence = (TextComposite) actualComponents.get(2);
        Symbol secondSpace = (Symbol) actualComponents.get(3);
        TextComposite actualThirdSentence = (TextComposite) actualComponents.get(4);
        Symbol thirdSpace = (Symbol) actualComponents.get(5);
        TextComposite actualFourthSentence = (TextComposite) actualComponents.get(6);

        Assert.assertEquals(firstSentence, actualFirstSentence);
        Assert.assertEquals(SPACE, firstSpace);
        Assert.assertEquals(secondSentence, actualSecondSentence);
        Assert.assertEquals(SPACE, secondSpace);
        Assert.assertEquals(thirdSentence, actualThirdSentence);
        Assert.assertEquals(SPACE, thirdSpace);
        Assert.assertEquals(fourthSentence, actualFourthSentence);
    }

    @Test
    public void shouldParagraphParsingFailed(){
        String incorrectSentenceContent = "Dogs eats i bones. Cata licsk milkd. Dogs eata milsk cadt. Caat lsick q basone.";
        TextComposite actualParagraph = (TextComposite) paragraphParser.parseTextComponent(incorrectSentenceContent);
        List<TextComponent> actualComponents = actualParagraph.getTextComponents();

        TextComposite actualFirstSentence = (TextComposite) actualComponents.get(0);
        Symbol firstSpace = (Symbol) actualComponents.get(1);
        TextComposite actualSecondSentence = (TextComposite) actualComponents.get(2);
        Symbol secondSpace = (Symbol) actualComponents.get(3);
        TextComposite actualThirdSentence = (TextComposite) actualComponents.get(4);
        Symbol thirdSpace = (Symbol) actualComponents.get(5);
        TextComposite actualFourthSentence = (TextComposite) actualComponents.get(6);

        Assert.assertNotEquals(firstSentence, actualFirstSentence);
        Assert.assertEquals(SPACE, firstSpace);
        Assert.assertNotEquals(secondSentence, actualSecondSentence);
        Assert.assertEquals(SPACE, secondSpace);
        Assert.assertNotEquals(thirdSentence, actualThirdSentence);
        Assert.assertEquals(SPACE, thirdSpace);
        Assert.assertNotEquals(fourthSentence, actualFourthSentence);
    }
}
