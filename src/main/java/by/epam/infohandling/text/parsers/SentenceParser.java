package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;

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
        TextComposite sentence = new TextComposite();
        sentence.setComponentType(ComponentType.SENTENCE);

        setNextParser(LexemeParser.getInstance());

        String[] lexemes = content.split(LEXEME_SPLIT_IDENTIFIER);
        Symbol space = new Symbol(LEXEME_SPLIT_IDENTIFIER, SymbolType.PUNCTUATION);

        for (int arrayIndex = 0; arrayIndex < lexemes.length; arrayIndex++) {
            String currentContent = lexemes[arrayIndex];
            TextComponent currentComponent = nextParser.parseTextComponent(currentContent);
            sentence.addTextComponent(currentComponent);

            if (arrayIndex != lexemes.length - LAST_LEXEME_IDENTIFIER){
                sentence.addTextComponent(space);
            }
        }

        return sentence;
    }

    private void setNextParser(Parser nextParser) {
        this.nextParser = nextParser;
    }

}
