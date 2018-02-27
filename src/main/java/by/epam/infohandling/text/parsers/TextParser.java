package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser extends Parser {

    private static final String PARAGRAPH_PATTERN = "\\t?[\\p{Upper}+\\-(](.(?!\\r?\\n\\r?\\n))*.";

    @Override
    public TextComponent parseTextComponent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Incorrect content.");
        }

        TextComposite text = new TextComposite();
        text.setComponentType(ComponentType.TEXT);

        Symbol newLine = new Symbol("\n\t", SymbolType.PUNCTUATION);
        Symbol tabulation = new Symbol("\t", SymbolType.PUNCTUATION);
        text.addTextComponent(tabulation);

        Pattern pattern = Pattern.compile(PARAGRAPH_PATTERN);
        Matcher matcher = pattern.matcher(content);

        int matcherGroupCount = matcher.groupCount();
        if (matcherGroupCount <= 0) {
            throw new IllegalArgumentException("Incorrect text format detected.");
        }

        while (matcher.find()) {
            String currentContent = matcher.group();
            currentContent = currentContent.trim();

            TextComposite paragraph = (TextComposite) nextParser.parseTextComponent(currentContent);

            text.addTextComponent(paragraph);
            text.addTextComponent(newLine);
        }

        text.removeLastTextComponent();
        return text;
    }

}
