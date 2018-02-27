package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.TextComposite;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextParserTest {

    private static final String TEXT_CONTENT_EXAMPLE = "\tDog eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.\n" +
            "\tCat eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.\n" +
            "\tBird eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.";

    private static TextParser textParser;

    private static TextComposite firstParagraph;
    private static TextComposite secondParagraph;
    private static TextComposite thirdParagraph;

    @BeforeClass
    public static void setTextParser() {
        textParser = new TextParser();
    }

    @BeforeClass
    public static void setTestingObjects() {
        firstParagraph = mock(TextComposite.class);
        when(firstParagraph.getContent()).thenReturn("Dog eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.");

        secondParagraph = mock(TextComposite.class);
        when(secondParagraph.getContent()).thenReturn("Cat eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.");

        thirdParagraph = mock(TextComposite.class);
        when(thirdParagraph.getContent()).thenReturn("Bird eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.");

    }

    @Test
    public void shouldTextParsingBeSuccessful() {
        ParagraphParser paragraphParser = mock(ParagraphParser.class);
        when(paragraphParser.parseTextComponent("Dog eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone."))
                .thenReturn(firstParagraph);
        when(paragraphParser.parseTextComponent("Cat eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone."))
                .thenReturn(secondParagraph);
        when(paragraphParser.parseTextComponent("Bird eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone."))
                .thenReturn(thirdParagraph);

        textParser.setNextParser(paragraphParser);
        TextComposite actualText = (TextComposite) textParser.parseTextComponent(TEXT_CONTENT_EXAMPLE);
        String actualContent = actualText.getContent();

        Assert.assertEquals("\tDog eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.\n" +
                "\tCat eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.\n" +
                "\tBird eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.", actualContent);
    }

}
