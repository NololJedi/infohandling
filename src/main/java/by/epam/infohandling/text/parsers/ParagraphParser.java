package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.infohandling.util.ContentMatcher.LAST_ELEMENT_IDENTIFIER;
import static by.epam.infohandling.util.ContentMatcher.SPACE;

public class ParagraphParser extends Parser {

    private static final String SENTENCE_PATTERN = "[\\p{Upper}+\\-(](.(?!\\.))*..";

    @Override
    public TextComponent parseTextComponent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Incorrect content.");
        }

        TextComposite paragraph = new TextComposite();
        paragraph.setComponentType(ComponentType.PARAGRAPH);
        Symbol space = new Symbol(SPACE, SymbolType.PUNCTUATION);

        Pattern pattern = Pattern.compile(SENTENCE_PATTERN);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String currentContent = matcher.group();
            TextComposite sentence = (TextComposite) nextParser.parseTextComponent(currentContent);

            paragraph.addTextComponent(sentence);
            paragraph.addTextComponent(space);
        }

        paragraph.removeLastTextComponent();
        return paragraph;
    }

}
