package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.Symbol;
import by.epam.infohandling.text.composite.SymbolType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.lexeme.Lexeme;
import by.epam.infohandling.text.composite.lexeme.LexemeType;
import by.epam.infohandling.util.ContentMatcher;

import static by.epam.infohandling.util.ContentMatcher.LEXEME_WORD_PATTERN;
import static by.epam.infohandling.util.ContentMatcher.SYMBOL_LENGTH;

public class LexemeParser extends Parser {

    @Override
    public TextComponent parseTextComponent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Incorrect content.");
        }
        if (nextParser == null){
            throw new IllegalArgumentException("Incorrect parser detected.");
        }
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

        boolean isLexemeTypeWord = ContentMatcher.contentMatch(content, LEXEME_WORD_PATTERN);

        if (isLexemeTypeWord) {
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

}
