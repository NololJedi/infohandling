package by.epam.infohandling.text.parsers;

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
        firstSentence = mock(TextComposite.class);
        when(firstSentence.getContent()).thenReturn("Dog eat a bone.");

        secondSentence = mock(TextComposite.class);
        when(secondSentence.getContent()).thenReturn("Cat lick milk.");

        thirdSentence = mock(TextComposite.class);
        when(thirdSentence.getContent()).thenReturn("Dog eat milk cat.");

        fourthSentence = mock(TextComposite.class);
        when(fourthSentence.getContent()).thenReturn("Cat lick a bone.");
    }

    @Test
    public void shouldParagraphParsingBeSuccessful() {
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


}
