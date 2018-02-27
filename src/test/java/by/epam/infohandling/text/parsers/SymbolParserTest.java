package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.Symbol;
import by.epam.infohandling.text.composite.SymbolType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.TextComposite;
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
    public static void setSymbolParser(){
        symbolParser = new SymbolParser();
    }

    @Test
    public void shouldAlphabetContentParsingBeSuccessful() {
        List<TextComponent> components = getComponents(ALPHABET_CONTENT);

        Symbol firstSymbol = (Symbol) components.get(0);
        checkSymbol(firstSymbol, ALPHABET, "a");

        Symbol secondSymbol = (Symbol) components.get(1);
        checkSymbol(secondSymbol, ALPHABET, "b");

        Symbol thirdSymbol = (Symbol) components.get(2);
        checkSymbol(thirdSymbol, ALPHABET, "S");

    }

    @Test
    public void shouldMathContentParsingBeSuccessful() {
        List<TextComponent> components = getComponents(MATH_CONTENT);

        Symbol firstSymbol = (Symbol) components.get(0);
        checkSymbol(firstSymbol, MATH, "-");

        Symbol secondSymbol = (Symbol) components.get(1);
        checkSymbol(secondSymbol, MATH, "(");

        Symbol thirdSymbol = (Symbol) components.get(2);
        checkSymbol(thirdSymbol, MATH, ")");

        Symbol fourthSymbol = (Symbol) components.get(3);
        checkSymbol(fourthSymbol, MATH, "*");

        Symbol fifthSymbol = (Symbol) components.get(4);
        checkSymbol(fifthSymbol, MATH, "+");

        Symbol sixSymbol = (Symbol) components.get(5);
        checkSymbol(sixSymbol, MATH, "/");
    }

    @Test
    public void shouldNumberContentParsingBeSuccessful() {
        List<TextComponent> components = getComponents(NUMBER_CONTENT);

        Symbol firstSymbol = (Symbol) components.get(0);
        checkSymbol(firstSymbol, NUMBER, "1");

        Symbol secondSymbol = (Symbol) components.get(1);
        checkSymbol(secondSymbol, NUMBER, "2");

        Symbol thirdSymbol = (Symbol) components.get(2);
        checkSymbol(thirdSymbol, NUMBER, "3");

        Symbol fourthSymbol = (Symbol) components.get(3);
        checkSymbol(fourthSymbol, NUMBER, "4");

        Symbol fifthSymbol = (Symbol) components.get(4);
        checkSymbol(fifthSymbol, NUMBER, "5");
    }

    @Test
    public void shouldPunctuationContentParsingBeSuccessful() {
        List<TextComponent> components = getComponents(PUNCTUATION_CONTENT);

        Symbol firstSymbol = (Symbol) components.get(0);
        checkSymbol(firstSymbol, PUNCTUATION, ",");

        Symbol secondSymbol = (Symbol) components.get(1);
        checkSymbol(secondSymbol, PUNCTUATION, ".");

        Symbol thirdSymbol = (Symbol) components.get(2);
        checkSymbol(thirdSymbol, PUNCTUATION, "?");

        Symbol fourthSymbol = (Symbol) components.get(3);
        checkSymbol(fourthSymbol, PUNCTUATION, "!");

        Symbol fifthSymbol = (Symbol) components.get(4);
        checkSymbol(fifthSymbol, PUNCTUATION, " ");
    }


    private void checkSymbol(Symbol symbol, SymbolType expectedType, String expectedContent) {
        SymbolType actualSymbolType = symbol.getSymbolType();
        Assert.assertEquals(expectedType, actualSymbolType);

        String actualContent = symbol.getContent();
        Assert.assertEquals(expectedContent, actualContent);
    }

    private List<TextComponent> getComponents(String content) {
        TextComposite symbols = (TextComposite) symbolParser.parseTextComponent(content);
        List<TextComponent> components = symbols.getTextComponents();

        return components;
    }

}
