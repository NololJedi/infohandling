package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.Symbol;
import by.epam.infohandling.text.composite.SymbolType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.TextComposite;

import static by.epam.infohandling.util.ContentMatcher.*;

public class SymbolParser extends Parser {

    @Override
    public void setNextParser(Parser nextParser) {
        throw new UnsupportedOperationException("Final parser of chain. Operation not supported.");
    }

    @Override
    public TextComponent parseTextComponent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Incorrect content.");
        }

        if (content.length() == SYMBOL_LENGTH) {
            Symbol symbol = new Symbol(content);
            SymbolType symbolType = identifySymbolType(content);
            symbol.setSymbolType(symbolType);

            return symbol;
        } else {
            TextComposite symbols = new TextComposite();
            char[] characters = content.toCharArray();

            for (char currentCharacter : characters) {
                String currentContent = String.valueOf(currentCharacter);

                TextComponent symbol = parseTextComponent(currentContent);
                symbols.addTextComponent(symbol);
            }

            return symbols;
        }
    }

    private SymbolType identifySymbolType(String content) {
        boolean alphabetMatch = contentMatch(content, SYMBOL_ALPHABET_PATTERN);
        if (alphabetMatch) {
            return SymbolType.ALPHABET;
        }

        boolean mathMatch = contentMatch(content, SYMBOL_MATH_PATTERN);
        if (mathMatch) {
            return SymbolType.MATH;
        }

        boolean punctuationMatch = contentMatch(content, SYMBOL_PUNCTUATION_PATTERN);
        if (punctuationMatch) {
            return SymbolType.PUNCTUATION;
        }

        boolean numberMatch = contentMatch(content, SYMBOL_NUMBER_PATTERN);
        if (numberMatch) {
            return SymbolType.NUMBER;
        }

        throw new IllegalArgumentException("Content can't be identified.");
    }

}
