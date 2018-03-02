package by.epam.infohandling;

import by.epam.infohandling.text.composite.*;
import by.epam.infohandling.util.ComponentExtractor;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class ComponentExtractorTest {

    private static TextComposite sentence;

    @BeforeClass
    public static void setSentence() {
        sentence = new TextComposite();
        sentence.setComponentType(ComponentType.SENTENCE);

        Lexeme wordDog = new Lexeme("Dog", ComponentType.WORD);
        Lexeme wordCat = new Lexeme("cat", ComponentType.WORD);
        Lexeme wordEat = new Lexeme("eat", ComponentType.WORD);
        PunctuationSymbol dot = new PunctuationSymbol(".");
        Lexeme math = new Lexeme("2+3", ComponentType.MATH_EXPRESSION);

        sentence.addTextComponent(wordDog);
        sentence.addTextComponent(wordEat);
        sentence.addTextComponent(wordCat);
        sentence.addTextComponent(math);
        sentence.addTextComponent(dot);

    }

    @Test
    public void shouldComponentsBeExtractedSuccessfully() {
        List<TextComponent> words = ComponentExtractor.extractComponents(sentence, ComponentType.WORD);

        TextComponent firstComponent = words.get(0);
        checkComponent(firstComponent, ComponentType.WORD, "Dog");

        TextComponent secondComponent = words.get(1);
        checkComponent(secondComponent, ComponentType.WORD, "eat");

        TextComponent thirdComponent = words.get(2);
        checkComponent(thirdComponent, ComponentType.WORD, "cat");
    }

    private void checkComponent(TextComponent component, ComponentType expectedType, String expectedContent) {
        String actualContent = component.getContent();
        Assert.assertEquals(expectedContent, actualContent);

        ComponentType actualType = component.getComponentType();
        Assert.assertEquals(expectedType, actualType);
    }

}
