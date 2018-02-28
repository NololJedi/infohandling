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
        firstParagraph = new TextComposite();
        firstParagraph.addTextComponent(
                new Lexeme("Dog eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.", ComponentType.PARAGRAPH));

        secondParagraph = new TextComposite();
        secondParagraph.addTextComponent(
                new Lexeme("Cat eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.", ComponentType.PARAGRAPH));

        thirdParagraph = new TextComposite();
        thirdParagraph.addTextComponent(
                new Lexeme("Bird eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.", ComponentType.PARAGRAPH));

    }

    @Test
    public void shouldTextChainParsingBeSuccessful() {
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

    @Test
    public void shouldTextWithOutChainParsingBeSuccessful() {
        TextComponent text = textParser.parseTextComponent(TEXT_CONTENT_EXAMPLE);
        String actualContent = text.getContent();

        Assert.assertEquals("\tDog eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.\n" +
                "\tCat eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.\n" +
                "\tBird eat a bone. Cat lick milk. Dog eat milk cat. Cat lick a bone.", actualContent);

        ComponentType actualType = text.getComponentType();

        Assert.assertEquals(ComponentType.TEXT, actualType);
    }
}
