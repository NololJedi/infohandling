package by.epam.infohandling;

import by.epam.infohandling.text.composite.Symbol;
import by.epam.infohandling.text.composite.SymbolType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.TextComposite;
import by.epam.infohandling.text.parsers.SymbolParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static by.epam.infohandling.text.composite.SymbolType.*;

public class SymbolParserTest {

    private static final String ALPHABET_CONTENT = "abS";
    private static final String MATH_CONTENT = "-()*+/";
    private static final String NUMBER_CONTENT = "12345";
    private static final String PUNCTUATION_CONTENT = ",.?! ";

    private static SymbolParser symbolParser;

    @BeforeClass
    public static void setSymbolParser() {
        symbolParser = SymbolParser.getInstance();
    }

    @Test
    public void shouldAlphabetContentParsingBeSuccessful() {
        List<TextComponent> components = getComponents(ALPHABET_CONTENT);

        Symbol firstSymbol = (Symbol) components.get(0);
        SymbolType firstSymbolType = firstSymbol.getSymbolType();
        Assert.assertEquals(ALPHABET, firstSymbolType);

        Symbol secondSymbol = (Symbol) components.get(1);
        SymbolType secondSymbolType = secondSymbol.getSymbolType();
        Assert.assertEquals(ALPHABET, secondSymbolType);

        Symbol thirdSymbol = (Symbol) components.get(2);
        SymbolType thirdSymbolType = thirdSymbol.getSymbolType();
        Assert.assertEquals(ALPHABET, thirdSymbolType);
    }

    @Test
    public void shouldAlphabetContentParsingFailed() {
        List<TextComponent> components = getComponents(ALPHABET_CONTENT);

        Symbol firstSymbol = (Symbol) components.get(0);
        SymbolType firstSymbolType = firstSymbol.getSymbolType();
        Assert.assertNotEquals(MATH, firstSymbolType);

        Symbol secondSymbol = (Symbol) components.get(1);
        SymbolType secondSymbolType = secondSymbol.getSymbolType();
        Assert.assertNotEquals(PUNCTUATION, secondSymbolType);

        Symbol thirdSymbol = (Symbol) components.get(2);
        SymbolType thirdSymbolType = thirdSymbol.getSymbolType();
        Assert.assertNotEquals(NUMBER, thirdSymbolType);

    }

    @Test
    public void shouldMathContentParsingBeSuccessful() {
        List<TextComponent> components = getComponents(MATH_CONTENT);

        Symbol firstSymbol = (Symbol) components.get(0);
        SymbolType firstSymbolType = firstSymbol.getSymbolType();
        Assert.assertEquals(MATH, firstSymbolType);

        Symbol secondSymbol = (Symbol) components.get(1);
        SymbolType secondSymbolType = secondSymbol.getSymbolType();
        Assert.assertEquals(MATH, secondSymbolType);

        Symbol thirdSymbol = (Symbol) components.get(2);
        SymbolType thirdSymbolType = thirdSymbol.getSymbolType();
        Assert.assertEquals(MATH, thirdSymbolType);

        Symbol fourthSymbol = (Symbol) components.get(3);
        SymbolType fourthSymbolType = fourthSymbol.getSymbolType();
        Assert.assertEquals(MATH, fourthSymbolType);

        Symbol fifthSymbol = (Symbol) components.get(4);
        SymbolType fifthSymbolType = fifthSymbol.getSymbolType();
        Assert.assertEquals(MATH, fifthSymbolType);

        Symbol sixSymbol = (Symbol) components.get(5);
        SymbolType sixSymbolType = sixSymbol.getSymbolType();
        Assert.assertEquals(MATH, sixSymbolType);
    }

    @Test
    public void shouldMathContentParsingFailed() {
        List<TextComponent> components = getComponents(MATH_CONTENT);

        Symbol firstSymbol = (Symbol) components.get(0);
        SymbolType firstSymbolType = firstSymbol.getSymbolType();
        Assert.assertNotEquals(ALPHABET, firstSymbolType);

        Symbol secondSymbol = (Symbol) components.get(1);
        SymbolType secondSymbolType = secondSymbol.getSymbolType();
        Assert.assertNotEquals(NUMBER, secondSymbolType);

        Symbol thirdSymbol = (Symbol) components.get(2);
        SymbolType thirdSymbolType = thirdSymbol.getSymbolType();
        Assert.assertNotEquals(ALPHABET, thirdSymbolType);

        Symbol fourthSymbol = (Symbol) components.get(3);
        SymbolType fourthSymbolType = fourthSymbol.getSymbolType();
        Assert.assertNotEquals(PUNCTUATION, fourthSymbolType);

        Symbol fifthSymbol = (Symbol) components.get(4);
        SymbolType fifthSymbolType = fifthSymbol.getSymbolType();
        Assert.assertNotEquals(NUMBER, fifthSymbolType);

        Symbol sixSymbol = (Symbol) components.get(5);
        SymbolType sixSymbolType = sixSymbol.getSymbolType();
        Assert.assertNotEquals(ALPHABET, sixSymbolType);
    }

    @Test
    public void shouldNumberContentParsingBeSuccessful() {
        List<TextComponent> components = getComponents(NUMBER_CONTENT);

        Symbol firstSymbol = (Symbol) components.get(0);
        SymbolType firstSymbolType = firstSymbol.getSymbolType();
        Assert.assertEquals(NUMBER, firstSymbolType);

        Symbol secondSymbol = (Symbol) components.get(1);
        SymbolType secondSymbolType = secondSymbol.getSymbolType();
        Assert.assertEquals(NUMBER, secondSymbolType);

        Symbol thirdSymbol = (Symbol) components.get(2);
        SymbolType thirdSymbolType = thirdSymbol.getSymbolType();
        Assert.assertEquals(NUMBER, thirdSymbolType);

        Symbol fourthSymbol = (Symbol) components.get(3);
        SymbolType fourthSymbolType = fourthSymbol.getSymbolType();
        Assert.assertEquals(NUMBER, fourthSymbolType);

        Symbol fifthSymbol = (Symbol) components.get(4);
        SymbolType fifthSymbolType = fifthSymbol.getSymbolType();
        Assert.assertEquals(NUMBER, fifthSymbolType);
    }

    @Test
    public void shouldNumberContentParsingFailed() {
        List<TextComponent> components = getComponents(NUMBER_CONTENT);

        Symbol firstSymbol = (Symbol) components.get(0);
        SymbolType firstSymbolType = firstSymbol.getSymbolType();
        Assert.assertNotEquals(ALPHABET, firstSymbolType);

        Symbol secondSymbol = (Symbol) components.get(1);
        SymbolType secondSymbolType = secondSymbol.getSymbolType();
        Assert.assertNotEquals(MATH, secondSymbolType);

        Symbol thirdSymbol = (Symbol) components.get(2);
        SymbolType thirdSymbolType = thirdSymbol.getSymbolType();
        Assert.assertNotEquals(ALPHABET, thirdSymbolType);

        Symbol fourthSymbol = (Symbol) components.get(3);
        SymbolType fourthSymbolType = fourthSymbol.getSymbolType();
        Assert.assertNotEquals(PUNCTUATION, fourthSymbolType);

        Symbol fifthSymbol = (Symbol) components.get(4);
        SymbolType fifthSymbolType = fifthSymbol.getSymbolType();
        Assert.assertNotEquals(MATH, fifthSymbolType);
    }

    @Test
    public void shouldPunctuationContentParsingBeSuccessful() {
        List<TextComponent> components = getComponents(PUNCTUATION_CONTENT);

        Symbol firstSymbol = (Symbol) components.get(0);
        SymbolType firstSymbolType = firstSymbol.getSymbolType();
        Assert.assertEquals(PUNCTUATION, firstSymbolType);

        Symbol secondSymbol = (Symbol) components.get(1);
        SymbolType secondSymbolType = secondSymbol.getSymbolType();
        Assert.assertEquals(PUNCTUATION, secondSymbolType);

        Symbol thirdSymbol = (Symbol) components.get(2);
        SymbolType thirdSymbolType = thirdSymbol.getSymbolType();
        Assert.assertEquals(PUNCTUATION, thirdSymbolType);

        Symbol fourthSymbol = (Symbol) components.get(3);
        SymbolType fourthSymbolType = fourthSymbol.getSymbolType();
        Assert.assertEquals(PUNCTUATION, fourthSymbolType);

        Symbol fifthSymbol = (Symbol) components.get(4);
        SymbolType fifthSymbolType = fifthSymbol.getSymbolType();
        Assert.assertEquals(PUNCTUATION, fifthSymbolType);
    }

    @Test
    public void shouldPunctuationContentParsingFailed() {
        List<TextComponent> components = getComponents(PUNCTUATION_CONTENT);

        Symbol firstSymbol = (Symbol) components.get(0);
        SymbolType firstSymbolType = firstSymbol.getSymbolType();
        Assert.assertNotEquals(ALPHABET, firstSymbolType);

        Symbol secondSymbol = (Symbol) components.get(1);
        SymbolType secondSymbolType = secondSymbol.getSymbolType();
        Assert.assertNotEquals(MATH, secondSymbolType);

        Symbol thirdSymbol = (Symbol) components.get(2);
        SymbolType thirdSymbolType = thirdSymbol.getSymbolType();
        Assert.assertNotEquals(ALPHABET, thirdSymbolType);

        Symbol fourthSymbol = (Symbol) components.get(3);
        SymbolType fourthSymbolType = fourthSymbol.getSymbolType();
        Assert.assertNotEquals(NUMBER, fourthSymbolType);

        Symbol fifthSymbol = (Symbol) components.get(4);
        SymbolType fifthSymbolType = fifthSymbol.getSymbolType();
        Assert.assertNotEquals(MATH, fifthSymbolType);
    }

    private List<TextComponent> getComponents(String content) {
        TextComposite symbols = (TextComposite) symbolParser.parseTextComponent(content);
        List<TextComponent> components = symbols.getTextComponents();

        return components;
    }
}
