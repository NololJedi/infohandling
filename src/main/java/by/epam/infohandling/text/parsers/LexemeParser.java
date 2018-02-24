package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;
import by.epam.infohandling.text.composite.lexeme.Lexeme;
import by.epam.infohandling.text.composite.lexeme.LexemeType;

import static by.epam.infohandling.text.parsers.ContentMatcher.LEXEME_WORD_PATTERN;
import static by.epam.infohandling.text.parsers.ContentMatcher.SYMBOL_LENGTH;

public class LexemeParser implements Parser {

    private static LexemeParser lexemeParser = null;

    private Parser nextParser;

    private LexemeParser() {
    }

    public static LexemeParser getInstance() {
        if (lexemeParser == null) {
            lexemeParser = new LexemeParser();
        }

        return lexemeParser;
    }

    @Override
    public TextComponent parseTextComponent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Incorrect content.");
        }
        setNextParser(SymbolParser.getInstance());
        Lexeme lexeme = new Lexeme();

        if (content.length() == SYMBOL_LENGTH) {
            Symbol symbol = (Symbol) nextParser.parseTextComponent(content);

            if (symbol.getSymbolType() == SymbolType.ALPHABET) {
                lexeme.addTextComponent(symbol);
                lexeme.setLexemeType(LexemeType.WORD);
                return lexeme;
            } else {

                return symbol;
            }
        }

        boolean lexemeTypeMatch = ContentMatcher.contentMatch(content,LEXEME_WORD_PATTERN);

        if (lexemeTypeMatch){
            lexeme.setLexemeType(LexemeType.WORD);
        } else {
            lexeme.setLexemeType(LexemeType.MATH_EXPRESSION);
        }

        char[] contentSymbols = content.toCharArray();
        for (char contentSymbol : contentSymbols) {
            String currentContent = String.valueOf(contentSymbol);
            TextComponent component = nextParser.parseTextComponent(currentContent);

            lexeme.addTextComponent(component);
        }

        return lexeme;
    }

    private void setNextParser(Parser nextParser) {
        this.nextParser = nextParser;
    }
}
