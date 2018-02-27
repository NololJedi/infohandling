package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.Symbol;
import by.epam.infohandling.text.composite.TextComposite;
import by.epam.infohandling.text.composite.lexeme.Lexeme;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SentenceParserTest {

    private static final String SENTENCE_CONTENT_EXAMPLE = "Dog eat a bone, cat 3*3+(3*3) milk.";

    private static SentenceParser sentenceParser;

    private static Lexeme wordDog;
    private static Lexeme wordEat;
    private static Lexeme article;
    private static Lexeme wordBone;
    private static Lexeme wordCat;
    private static Lexeme wordMilk;
    private static Lexeme mathExpression;
    private static Symbol commaSymbol;
    private static Symbol dotSymbol;

    @BeforeClass
    public static void setTestingObjects() {
        wordDog = mock(Lexeme.class);
        when(wordDog.getContent()).thenReturn("Dog");

        wordEat = mock(Lexeme.class);
        when(wordEat.getContent()).thenReturn("eat");

        article = mock(Lexeme.class);
        when(article.getContent()).thenReturn("a");

        wordBone = mock(Lexeme.class);
        when(wordBone.getContent()).thenReturn("bone");

        wordCat = mock(Lexeme.class);
        when(wordCat.getContent()).thenReturn("cat");

        wordMilk = mock(Lexeme.class);
        when(wordMilk.getContent()).thenReturn("milk");

        mathExpression = mock(Lexeme.class);
        when(mathExpression.getContent()).thenReturn("3*3+(3*3)");

        dotSymbol = mock(Symbol.class);
        when(dotSymbol.getContent()).thenReturn(".");

        commaSymbol = mock(Symbol.class);
        when(commaSymbol.getContent()).thenReturn(",");
    }

    @BeforeClass
    public static void setSentenceParser() {
        sentenceParser = new SentenceParser();
    }

    @Test
    public void shouldSentenceParsingBeSuccessful() {

        LexemeParser lexemeParser = mock(LexemeParser.class);
        when(lexemeParser.parseTextComponent("Dog")).thenReturn(wordDog);
        when(lexemeParser.parseTextComponent("eat")).thenReturn(wordEat);
        when(lexemeParser.parseTextComponent("a")).thenReturn(article);
        when(lexemeParser.parseTextComponent("bone")).thenReturn(wordBone);
        when(lexemeParser.parseTextComponent("cat")).thenReturn(wordCat);
        when(lexemeParser.parseTextComponent("3*3+(3*3)")).thenReturn(mathExpression);
        when(lexemeParser.parseTextComponent("milk")).thenReturn(wordMilk);
        when(lexemeParser.parseTextComponent(",")).thenReturn(commaSymbol);
        when(lexemeParser.parseTextComponent(".")).thenReturn(dotSymbol);

        sentenceParser.setNextParser(lexemeParser);
        TextComposite actualSentence = (TextComposite) sentenceParser.parseTextComponent(SENTENCE_CONTENT_EXAMPLE);
        String content = actualSentence.getContent();

        Assert.assertEquals("Dog eat a bone, cat 3*3+(3*3) milk.", content);
    }

}
