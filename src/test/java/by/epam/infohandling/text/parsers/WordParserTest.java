package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.Symbol;
import by.epam.infohandling.text.composite.SymbolType;
import by.epam.infohandling.text.composite.TextComposite;
import by.epam.infohandling.text.parsers.WordParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WordParserTest {

    private static final String articleContent = "a";
    private static final String wordContent = "Dog";

    private static TextComposite article;
    private static TextComposite word;

    private static WordParser wordParser;

    @BeforeClass
    public static void setWordParser() {
        wordParser = WordParser.getInstance();
    }

    @BeforeClass
    public static void setTestingObjects() {
        Symbol symbol = new Symbol("a", SymbolType.ALPHABET);
        article = new TextComposite();
        article.setComponentType(ComponentType.WORD);
        article.addTextComponent(symbol);

        Symbol firstLetter = new Symbol("D", SymbolType.ALPHABET);
        Symbol secondLetter = new Symbol("o", SymbolType.ALPHABET);
        Symbol thirdLetter = new Symbol("g", SymbolType.ALPHABET);

        word = new TextComposite();
        word.setComponentType(ComponentType.WORD);
        word.addTextComponent(firstLetter);
        word.addTextComponent(secondLetter);
        word.addTextComponent(thirdLetter);
    }

    @Test
    public void shouldWordParsingBeSuccessful() {
        TextComposite actualWord = (TextComposite) wordParser.parseTextComponent(wordContent);

        Assert.assertEquals(word, actualWord);
    }

    @Test
    public void shouldWordParsingFailed() {
        String incorrectWordContent = "dog.";
        TextComposite actualWord = (TextComposite) wordParser.parseTextComponent(incorrectWordContent);

        Assert.assertNotEquals(word, actualWord);
    }

    @Test
    public void shouldArticleParsingBeSuccessful() {
        TextComposite actualArticle = (TextComposite) wordParser.parseTextComponent(articleContent);

        Assert.assertEquals(article, actualArticle);
    }

    @Test(expected = ClassCastException.class)
    public void shouldArticleParsingFailed() {
        String incorrectArticleContent = ".";
        TextComposite actualArticle = (TextComposite) wordParser.parseTextComponent(incorrectArticleContent);
    }
}
