package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;

import static by.epam.infohandling.text.parsers.ContentMatcher.LEXEME_IDENTIFIER;

public class LexemeParser implements Parser {

    private static LexemeParser lexemeParser = null;

    private Parser nextParser;

    private LexemeParser() {
    }

    private void setNextParser(Parser nextParser) {
        this.nextParser = nextParser;
    }

    public static LexemeParser getInstance() {

        if (lexemeParser == null) {
            lexemeParser = new LexemeParser();
        }

        return lexemeParser;
    }

    @Override
    public TextComponent parseTextComponent(String content) {
       setNextParser(WordParser.getInstance());

        TextComposite lexeme = new TextComposite();
        lexeme.setComponentType(ComponentType.LEXEME);

        Symbol spaceSymbol = new Symbol(LEXEME_IDENTIFIER, SymbolType.PUNCTUATION);
        lexeme.addTextComponent(spaceSymbol);

        content = content.trim();
        String[] words = content.split(LEXEME_IDENTIFIER);

        for (String word : words) {
            TextComposite currentWord = (TextComposite) nextParser.parseTextComponent(word);

            lexeme.addTextComponent(currentWord);
            lexeme.addTextComponent(spaceSymbol);
        }

        return lexeme;
    }

}
