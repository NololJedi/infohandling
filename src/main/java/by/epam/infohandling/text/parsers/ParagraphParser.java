package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.TextComposite;

public class ParagraphParser implements Parser {

    private static ParagraphParser paragraphParser = null;

    private Parser nextParser;

    private ParagraphParser() {
    }

    public static ParagraphParser getInstance() {
        if (paragraphParser == null) {
            paragraphParser = new ParagraphParser();
        }

        return paragraphParser;
    }

    @Override
    public TextComponent parseTextComponent(String content) {
        TextComposite paragraph = new TextComposite();
        paragraph.setComponentType(ComponentType.PARAGRAPH);

        setNextParser(SentenceParser.getInstance());


        return paragraph;
    }

    private void setNextParser(Parser nextParser) {
        this.nextParser = nextParser;
    }
}
