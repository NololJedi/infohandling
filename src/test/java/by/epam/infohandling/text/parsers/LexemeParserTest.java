package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;
import by.epam.infohandling.text.parsers.LexemeParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static by.epam.infohandling.text.parsers.ContentMatcher.LEXEME_IDENTIFIER;

public class LexemeParserTest {

    public static final String CONTENT = " lex pars test ";

    public static LexemeParser lexemeParser;

    public static TextComponent space;
    public static TextComposite lexemeWord;
    public static TextComposite parserWord;
    public static TextComposite testWord;

    @BeforeClass
    public static void setLexemeParser(){
        lexemeParser = LexemeParser.getInstance();
    }

    @BeforeClass
    public static void setTestingObjects(){
        space = new Symbol(LEXEME_IDENTIFIER, SymbolType.PUNCTUATION);

        Symbol letterL = new Symbol("l",SymbolType.ALPHABET);
        Symbol letterE = new Symbol("e",SymbolType.ALPHABET);
        Symbol letterX = new Symbol("x",SymbolType.ALPHABET);
        Symbol letterP = new Symbol("p",SymbolType.ALPHABET);
        Symbol letterA = new Symbol("a",SymbolType.ALPHABET);
        Symbol letterR = new Symbol("r",SymbolType.ALPHABET);
        Symbol letterS = new Symbol("s",SymbolType.ALPHABET);
        Symbol letterT = new Symbol("t",SymbolType.ALPHABET);

        lexemeWord = new TextComposite();
        lexemeWord.setComponentType(ComponentType.WORD);
        lexemeWord.addTextComponent(letterL);
        lexemeWord.addTextComponent(letterE);
        lexemeWord.addTextComponent(letterX);

        parserWord = new TextComposite();
        parserWord.setComponentType(ComponentType.WORD);
        parserWord.addTextComponent(letterP);
        parserWord.addTextComponent(letterA);
        parserWord.addTextComponent(letterR);
        parserWord.addTextComponent(letterS);

        testWord = new TextComposite();
        testWord.setComponentType(ComponentType.WORD);
        testWord.addTextComponent(letterT);
        testWord.addTextComponent(letterE);
        testWord.addTextComponent(letterS);
        testWord.addTextComponent(letterT);
    }

    @Test
    public void shouldLexemeParsingBeSuccessful(){
        TextComposite lexeme = (TextComposite) lexemeParser.parseTextComponent(CONTENT);
        List<TextComponent> lexemeComponents = lexeme.getTextComponents();

        TextComponent firstSpaceSymbol = lexemeComponents.get(0);
        Assert.assertEquals(space,firstSpaceSymbol);

        TextComponent actualLexemeWord = lexemeComponents.get(1);
        Assert.assertEquals(lexemeWord,actualLexemeWord);

        TextComponent secondSpaceSymbol = lexemeComponents.get(2);
        Assert.assertEquals(space, secondSpaceSymbol);

        TextComponent actualParserWord = lexemeComponents.get(3);
        Assert.assertEquals(parserWord,actualParserWord);

        TextComponent thirdSpaceSymbol = lexemeComponents.get(4);
        Assert.assertEquals(space, thirdSpaceSymbol);

        TextComponent actualTestWord = lexemeComponents.get(5);
        Assert.assertEquals(testWord,actualTestWord);

        TextComponent fourthSpaceSymbol = lexemeComponents.get(6);
        Assert.assertEquals(space, fourthSpaceSymbol);
    }

    @Test
    public void shouldLexemeParsingFailed(){

        TextComposite lexeme = (TextComposite) lexemeParser.parseTextComponent(CONTENT);
        List<TextComponent> lexemeComponents = lexeme.getTextComponents();

        TextComponent firstSpaceSymbol = lexemeComponents.get(0);
        Assert.assertNotEquals(lexemeWord,firstSpaceSymbol);

        TextComponent actualLexemeWord = lexemeComponents.get(1);
        Assert.assertNotEquals(space,actualLexemeWord);

        TextComponent secondSpaceSymbol = lexemeComponents.get(2);
        Assert.assertNotEquals(parserWord, secondSpaceSymbol);

        TextComponent actualParserWord = lexemeComponents.get(3);
        Assert.assertNotEquals(space,actualParserWord);

        TextComponent thirdSpaceSymbol = lexemeComponents.get(4);
        Assert.assertNotEquals(testWord, thirdSpaceSymbol);

        TextComponent actualTestWord = lexemeComponents.get(5);
        Assert.assertNotEquals(space,actualTestWord);


    }
}
