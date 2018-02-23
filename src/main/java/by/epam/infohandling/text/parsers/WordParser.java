package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.*;

import static by.epam.infohandling.text.parsers.ContentMatcher.SYMBOL_LENGTH;

public class WordParser implements Parser {

    private static WordParser wordParser = null;

    private WordParser() {
    }

    public static WordParser getInstance() {
        if (wordParser == null) {
            wordParser = new WordParser();
        }

        return wordParser;
    }

    @Override
    public TextComponent parseTextComponent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Incorrect content.");
        }
        Parser parser = SymbolParser.getInstance();
        TextComposite word = new TextComposite();
        word.setComponentType(ComponentType.WORD);

        if (content.length() == SYMBOL_LENGTH) {
            Symbol symbol = (Symbol) parser.parseTextComponent(content);

            if (symbol.getSymbolType() == SymbolType.ALPHABET) {
                word.addTextComponent(symbol);

                return word;
            } else {

                return symbol;
            }
        }

        char[] contentSymbols = content.toCharArray();
        for (char contentSymbol : contentSymbols) {
            String currentContent = String.valueOf(contentSymbol);
            TextComponent component = parser.parseTextComponent(currentContent);

            word.addTextComponent(component);
        }

        return word;
    }
}
