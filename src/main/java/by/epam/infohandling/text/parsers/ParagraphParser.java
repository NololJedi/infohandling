package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;

import static by.epam.infohandling.util.ContentMatcher.LAST_ELEMENT_IDENTIFIER;
import static by.epam.infohandling.util.ContentMatcher.SPACE;

public class ParagraphParser implements Parser {

    private static final String SPLIT_IDENTIFIER = "\\.\\s";

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

        Symbol space = new Symbol(SPACE, SymbolType.PUNCTUATION);

        String[] sentences = content.split(SPLIT_IDENTIFIER);
        for (int arrayIndex = 0; arrayIndex < sentences.length; arrayIndex++) {
            String currentContent = sentences[arrayIndex];
            currentContent += ".";

            TextComponent currentComponent = nextParser.parseTextComponent(currentContent);
            paragraph.addTextComponent(currentComponent);

            if (arrayIndex != sentences.length - LAST_ELEMENT_IDENTIFIER) {
                paragraph.addTextComponent(space);
            }
        }

        return paragraph;
    }

    private void setNextParser(Parser nextParser) {
        this.nextParser = nextParser;
    }
}
