package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.TextComposite;

public class WordParser implements Parser {

    private static WordParser wordParser = null;

    private WordParser(){}

    public static WordParser getInstance(){
        if (wordParser == null){
            wordParser = new WordParser();
        }

        return wordParser;
    }

    @Override
    public TextComponent parseTextComponent(String content) {
        if (content == null || content.isEmpty()){
            throw new IllegalArgumentException("Incorrect content.");
        }

        char[] contentSymbols = content.toCharArray();
        Parser parser = SymbolParser.getInstance();
        TextComposite word = new TextComposite();
        word.setComponentType(ComponentType.WORD);

        for (int arrayIndex = 0; arrayIndex < contentSymbols.length; arrayIndex++) {
            String currentContent = String.valueOf(contentSymbols[arrayIndex]);
            TextComponent component = parser.parseTextComponent(currentContent);

            word.addTextComponent(component);
        }

        return word;
    }
}
