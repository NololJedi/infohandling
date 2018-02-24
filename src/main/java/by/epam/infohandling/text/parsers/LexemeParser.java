package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.Symbol;
import by.epam.infohandling.text.composite.SymbolType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.lexeme.Lexeme;
import by.epam.infohandling.text.composite.lexeme.LexemeType;
import by.epam.infohandling.util.ContentMatcher;

import static by.epam.infohandling.util.ContentMatcher.*;

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

        boolean isLexemeTypeWord = ContentMatcher.contentMatch(content, LEXEME_WORD_PATTERN);

        if (isLexemeTypeWord) {
            lexeme.setLexemeType(LexemeType.WORD);
        } else {
            lexeme.setLexemeType(LexemeType.MATH_EXPRESSION);
        }

        int lastSymbolIdentifier = content.length() - 1;
        String lastSymbol = String.valueOf(content.charAt(lastSymbolIdentifier));

        boolean isLastSymbolPunctuation = ContentMatcher.contentMatch(lastSymbol, SYMBOL_PUNCTUATION_PATTERN);
        if (isLastSymbolPunctuation) {
            content = content.replaceAll(lastSymbol, "");

            TextComponent word = parseTextComponent(content);
            lexeme.addTextComponent(word);

            TextComponent punctuationSymbol = parseTextComponent(lastSymbol);
            lexeme.addTextComponent(punctuationSymbol);
        } else {
            char[] contentSymbols = content.toCharArray();
            for (char contentSymbol : contentSymbols) {
                String currentContent = String.valueOf(contentSymbol);
                TextComponent component = nextParser.parseTextComponent(currentContent);

                lexeme.addTextComponent(component);
            }
        }

        return lexeme;
    }

    private void setNextParser(Parser nextParser) {
        this.nextParser = nextParser;
    }
}
