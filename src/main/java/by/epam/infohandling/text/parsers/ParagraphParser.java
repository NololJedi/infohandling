package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.infohandling.util.ContentDeterminant.SENTENCE_PATTERN;
import static by.epam.infohandling.util.ContentDeterminant.SPACE;

public class ParagraphParser extends Parser {

    @Override
    public TextComponent parseTextComponent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Incorrect content.");
        }

        if (nextParser == null) {
            Lexeme paragraph = new Lexeme();
            paragraph.setContent(content);
            paragraph.setComponentType(ComponentType.PARAGRAPH);

            return paragraph;

        } else {
            TextComposite paragraph = new TextComposite();
            paragraph.setComponentType(ComponentType.PARAGRAPH);
            TextComponent space = new PunctuationSymbol(SPACE);

            Pattern pattern = Pattern.compile(SENTENCE_PATTERN);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                String currentContent = matcher.group();
                TextComponent sentence = nextParser.parseTextComponent(currentContent);

                paragraph.addTextComponent(sentence);
                paragraph.addTextComponent(space);
            }

            paragraph.removeLastTextComponent();
            return paragraph;
        }
    }

}
