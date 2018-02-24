package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;
import by.epam.infohandling.util.ContentMatcher;

import static by.epam.infohandling.util.ContentMatcher.SYMBOL_PUNCTUATION_PATTERN;

public class SentenceParser implements Parser {

    private static final String LEXEME_SPLIT_IDENTIFIER = " ";
    private static final int LAST_LEXEME_IDENTIFIER = 1;

    private static SentenceParser sentenceParser = null;

    private Parser nextParser;

    private SentenceParser() {
    }

    public static SentenceParser getInstance() {

        if (sentenceParser == null) {
            sentenceParser = new SentenceParser();
        }

        return sentenceParser;
    }

    @Override
    public TextComponent parseTextComponent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Incorrect content.");
        }

        TextComposite sentence = new TextComposite();
        sentence.setComponentType(ComponentType.SENTENCE);

        setNextParser(LexemeParser.getInstance());

        String[] lexemes = content.split(LEXEME_SPLIT_IDENTIFIER);
        Symbol space = new Symbol(LEXEME_SPLIT_IDENTIFIER, SymbolType.PUNCTUATION);

        for (int arrayIndex = 0; arrayIndex < lexemes.length; arrayIndex++) {
            String currentContent = lexemes[arrayIndex];

            int lastSymbolIdentifier = currentContent.length() - 1;
            String lastSymbol = String.valueOf(currentContent.charAt(lastSymbolIdentifier));

            boolean isLastSymbolPunctuation = ContentMatcher.contentMatch(lastSymbol, SYMBOL_PUNCTUATION_PATTERN);
            if (isLastSymbolPunctuation) {
                currentContent = currentContent.replace(lastSymbol, "");

                TextComponent word = nextParser.parseTextComponent(currentContent);
                sentence.addTextComponent(word);

                TextComponent punctuationSymbol = nextParser.parseTextComponent(lastSymbol);
                sentence.addTextComponent(punctuationSymbol);
            } else {
                TextComponent currentComponent = nextParser.parseTextComponent(currentContent);
                sentence.addTextComponent(currentComponent);
            }
            if (arrayIndex != lexemes.length - LAST_LEXEME_IDENTIFIER) {
                sentence.addTextComponent(space);
            }
        }

        return sentence;
    }

    private void setNextParser(Parser nextParser) {
        this.nextParser = nextParser;
    }

}
