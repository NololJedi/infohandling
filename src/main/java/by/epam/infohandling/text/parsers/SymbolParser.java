package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.Symbol;
import by.epam.infohandling.text.composite.TextComponent;

public class SymbolParser implements Parser {

    private static SymbolParser symbolParser = null;

    private SymbolParser(){}

    public static SymbolParser getInstance(){
        if (symbolParser == null) {
            symbolParser = new SymbolParser();
        }

        return symbolParser;
    }
    @Override
    public TextComponent parseTextComponent(String content) {
        if (content == null || content.isEmpty()){
            throw new IllegalArgumentException("Incorrect content.");
        }
        TextComponent symbol = new Symbol(content);

        return symbol;
    }
}
