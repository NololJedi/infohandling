package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;
import by.epam.infohandling.util.ContentDeterminant;

import static by.epam.infohandling.util.ContentDeterminant.*;

public class SentenceParser extends Parser {

    private static final String EMPTY_SYMBOL = "";

    @Override
    public TextComponent parseTextComponent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Incorrect content.");
        }

        if (nextParser == null) {
            Lexeme sentence = new Lexeme();
            sentence.setContent(content);
            sentence.setComponentType(ComponentType.SENTENCE);

            return sentence;

        } else {
            TextComposite sentence = new TextComposite();
            sentence.setComponentType(ComponentType.SENTENCE);
            TextComponent space = new PunctuationSymbol(SPACE);

            String[] lexemes = content.split(SPACE);


            for (int arrayIndex = 0; arrayIndex < lexemes.length; arrayIndex++) {
                String currentContent = lexemes[arrayIndex];

                int lastSymbolIdentifier = currentContent.length() - 1;
                char lastChar = currentContent.charAt(lastSymbolIdentifier);
                String lastSymbol = String.valueOf(lastChar);

                boolean isLastSymbolPunctuation = ContentDeterminant.matchContent(lastSymbol, PUNCTUATION_PATTERN);
                if (isLastSymbolPunctuation) {

                    if (currentContent.length() == SYMBOL_LENGTH){
                        TextComponent punctuationSymbol = new PunctuationSymbol(lastSymbol);
                        sentence.addTextComponent(punctuationSymbol);
                    } else {
                        currentContent = currentContent.replace(lastSymbol, EMPTY_SYMBOL);

                        TextComponent word = nextParser.parseTextComponent(currentContent);
                        sentence.addTextComponent(word);
                    }

                } else {
                    TextComponent currentComponent = nextParser.parseTextComponent(currentContent);
                    sentence.addTextComponent(currentComponent);
                }
                if (arrayIndex != lexemes.length - LAST_ELEMENT_IDENTIFIER) {
                    sentence.addTextComponent(space);
                }
            }

            return sentence;
        }
    }

}
