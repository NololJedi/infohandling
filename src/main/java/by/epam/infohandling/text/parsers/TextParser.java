package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;

import static by.epam.infohandling.util.ContentMatcher.LAST_ELEMENT_IDENTIFIER;

public class TextParser implements Parser {

    private static final String SPLIT_IDENTIFIER = "\\n";

    private static TextParser textParser = null;

    private Parser nextParser;

    private TextParser() {
    }

    public static TextParser getInstance() {
        if (textParser == null) {
            textParser = new TextParser();
        }

        return textParser;
    }

    @Override
    public TextComponent parseTextComponent(String content) {
        TextComposite text = new TextComposite();
        text.setComponentType(ComponentType.TEXT);

        setNextParser(ParagraphParser.getInstance());

        content = content.trim();

        Symbol newLine = new Symbol("\n", SymbolType.PUNCTUATION);
        Symbol tabulation = new Symbol("\t", SymbolType.PUNCTUATION);
        text.addTextComponent(tabulation);

        String[] paragraphs = content.split(SPLIT_IDENTIFIER);
        for (int arrayIndex = 0; arrayIndex < paragraphs.length; arrayIndex++) {
            String currentContent = paragraphs[arrayIndex];
            currentContent = currentContent.trim();
            TextComposite currentParagraph = (TextComposite) nextParser.parseTextComponent(currentContent);

            text.addTextComponent(currentParagraph);

            if (arrayIndex != paragraphs.length - LAST_ELEMENT_IDENTIFIER) {
                text.addTextComponent(newLine);
                text.addTextComponent(tabulation);
            }
        }

        return text;
    }

    private void setNextParser(Parser nextParser) {
        this.nextParser = nextParser;
    }
}
