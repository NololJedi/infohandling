package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;
import by.epam.infohandling.util.ContentMatcher;

import static by.epam.infohandling.util.ContentMatcher.*;

public class SentenceParser extends Parser {

    @Override
    public TextComponent parseTextComponent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Incorrect content.");
        }

        TextComposite sentence = new TextComposite();
        sentence.setComponentType(ComponentType.SENTENCE);

        String[] lexemes = content.split(SPACE);
        Symbol space = new Symbol(SPACE, SymbolType.PUNCTUATION);

        for (int arrayIndex = 0; arrayIndex < lexemes.length; arrayIndex++) {
            String currentContent = lexemes[arrayIndex];

            int lastSymbolIdentifier = currentContent.length() - 1;
            char lastChar = currentContent.charAt(lastSymbolIdentifier);
            String lastSymbol = String.valueOf(lastChar);

            boolean isLastSymbolPunctuation = ContentMatcher.contentMatch(lastSymbol, SYMBOL_PUNCTUATION_PATTERN);
            if (isLastSymbolPunctuation) {
                currentContent = currentContent.replace(lastSymbol, EMPTY_SYMBOL);

                TextComponent word = nextParser.parseTextComponent(currentContent);
                sentence.addTextComponent(word);

                TextComponent punctuationSymbol = nextParser.parseTextComponent(lastSymbol);
                sentence.addTextComponent(punctuationSymbol);
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
