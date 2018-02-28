package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SentenceParserTest {

    private static final String SENTENCE_CONTENT_EXAMPLE = "Dog eat a bone, cat 3*3+(3*3) milk.";

    private static SentenceParser sentenceParser;

    @BeforeClass
    public static void setSentenceParser() {
        sentenceParser = new SentenceParser();
    }

    @Test
    public void shouldSentenceChainParsingBeSuccessful() {

        LexemeParser lexemeParser = mock(LexemeParser.class);
        when(lexemeParser.parseTextComponent("Dog")).thenReturn(new Lexeme("Dog", ComponentType.WORD));
        when(lexemeParser.parseTextComponent("eat")).thenReturn(new Lexeme("eat", ComponentType.WORD));
        when(lexemeParser.parseTextComponent("a")).thenReturn(new Lexeme("a", ComponentType.ALPHABET_SYMBOL));
        when(lexemeParser.parseTextComponent("bone")).thenReturn(new Lexeme("bone", ComponentType.WORD));
        when(lexemeParser.parseTextComponent("cat")).thenReturn(new Lexeme("cat", ComponentType.WORD));
        when(lexemeParser.parseTextComponent("3*3+(3*3)")).thenReturn(new Lexeme("3*3+(3*3)", ComponentType.MATH_EXPRESSION));
        when(lexemeParser.parseTextComponent("milk")).thenReturn(new Lexeme("milk", ComponentType.WORD));
        when(lexemeParser.parseTextComponent(",")).thenReturn(new PunctuationSymbol(","));
        when(lexemeParser.parseTextComponent(".")).thenReturn(new PunctuationSymbol("."));

        sentenceParser.setNextParser(lexemeParser);
        TextComposite actualSentence = (TextComposite) sentenceParser.parseTextComponent(SENTENCE_CONTENT_EXAMPLE);
        String content = actualSentence.getContent();

        Assert.assertEquals("Dog eat a bone, cat 3*3+(3*3) milk.", content);
    }

    @Test
    public void shouldSentenceWithOutChainParsingBeSuccessful() {
        String expectedContent = "Dog eat a bone, cat 3*3+(3*3) milk.";
        TextComponent actualText = sentenceParser.parseTextComponent(SENTENCE_CONTENT_EXAMPLE);
        String actualContent = actualText.getContent();

        Assert.assertEquals(expectedContent, actualContent);

        ComponentType actualComponentType = actualText.getComponentType();

        Assert.assertEquals(ComponentType.SENTENCE, actualComponentType);
    }
}
