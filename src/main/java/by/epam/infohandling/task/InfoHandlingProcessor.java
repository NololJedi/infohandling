package by.epam.infohandling.task;

import by.epam.infohandling.exceptions.IncorrectFileException;
import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.TextComposite;
import by.epam.infohandling.text.parsers.LexemeParser;
import by.epam.infohandling.text.parsers.ParagraphParser;
import by.epam.infohandling.text.parsers.SentenceParser;
import by.epam.infohandling.text.parsers.TextParser;
import by.epam.infohandling.util.ComponentInjector;
import by.epam.infohandling.util.TextFileReader;

import java.util.List;

public class InfoHandlingProcessor {

    public static void main(String[] args) throws IncorrectFileException {

        String content = new TextFileReader().readTextFromFile("./src/main/resources/text.txt");

        System.out.println(content);

        LexemeParser lexemeParser = new LexemeParser();
        SentenceParser sentenceParser = new SentenceParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        sentenceParser.setNextParser(lexemeParser);
        paragraphParser.setNextParser(sentenceParser);

        TextParser textParser = new TextParser();
        textParser.setNextParser(paragraphParser);

        TextComposite text = (TextComposite) textParser.parseTextComponent(content);
        List<TextComponent> components = ComponentInjector.getComponentsByType(text,ComponentType.WORD);

        for (TextComponent component : components) {
            System.out.println("M " + component.getContent());
        }

    }

}
