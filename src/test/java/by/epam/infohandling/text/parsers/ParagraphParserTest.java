package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.Lexeme;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.TextComposite;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParagraphParserTest {

    private static final String PARAGRAPH_CONTENT_EXAMPLE = "Dog eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.";

    private static ParagraphParser paragraphParser;

    private static TextComposite firstSentence;
    private static TextComposite secondSentence;
    private static TextComposite thirdSentence;
    private static TextComposite fourthSentence;

    @BeforeClass
    public static void setParagraphParser() {
        paragraphParser = new ParagraphParser();
    }

    @BeforeClass
    public static void setTestingObjects() {
        firstSentence = new TextComposite();
        firstSentence.addTextComponent(new Lexeme("Dog eat a bone.", ComponentType.SENTENCE));

        secondSentence = new TextComposite();
        secondSentence.addTextComponent(new Lexeme("Cat lick milk.", ComponentType.SENTENCE));

        thirdSentence = new TextComposite();
        thirdSentence.addTextComponent(new Lexeme("Dog eat milk cat.", ComponentType.SENTENCE));

        fourthSentence = new TextComposite();
        fourthSentence.addTextComponent(new Lexeme("Cat lick a bone.", ComponentType.SENTENCE));
    }

    @Test
    public void shouldParagraphChainParsingBeSuccessful() {
        SentenceParser sentenceParser = mock(SentenceParser.class);
        when(sentenceParser.parseTextComponent("Dog eat a bone.")).thenReturn(firstSentence);
        when(sentenceParser.parseTextComponent("Cat lick milk.")).thenReturn(secondSentence);
        when(sentenceParser.parseTextComponent("Dog eat milk cat.")).thenReturn(thirdSentence);
        when(sentenceParser.parseTextComponent("Cat lick a bone.")).thenReturn(fourthSentence);

        paragraphParser.setNextParser(sentenceParser);
        TextComponent actualParagraph = paragraphParser.parseTextComponent(PARAGRAPH_CONTENT_EXAMPLE);
        String content = actualParagraph.getContent();

        Assert.assertEquals("Dog eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.", content);
    }

    @Test
    public void shouldParagraphWithOutChainParsingBeSuccessful() {
        TextComponent text = paragraphParser.parseTextComponent(PARAGRAPH_CONTENT_EXAMPLE);
        String actualContent = text.getContent();

        Assert.assertEquals("Dog eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.", actualContent);

        ComponentType actualType = text.getComponentType();

        Assert.assertEquals(ComponentType.PARAGRAPH, actualType);

    }

}
