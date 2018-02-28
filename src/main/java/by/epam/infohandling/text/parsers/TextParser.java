package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.infohandling.text.composite.ComponentType.TEXT;
import static by.epam.infohandling.util.ContentDeterminant.PARAGRAPH_PATTERN;

public class TextParser extends Parser {

    @Override
    public TextComponent parseTextComponent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Incorrect content.");
        }

        if (nextParser == null) {
            Lexeme text = new Lexeme();
            text.setContent(content);
            text.setComponentType(TEXT);

            return text;

        } else {
            TextComposite text = new TextComposite();
            text.setComponentType(ComponentType.TEXT);

            TextComponent newLine = new PunctuationSymbol("\n\t");
            TextComponent tabulation = new PunctuationSymbol("\t");
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

                TextComponent paragraph = nextParser.parseTextComponent(currentContent);

                text.addTextComponent(paragraph);
                text.addTextComponent(newLine);
            }

            text.removeLastTextComponent();
            return text;
        }
    }

}
