package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.TextComposite;

public class LexemeParser implements Parser {

    private static final String LEXEME_IDENTIFIER = " ";

    private static LexemeParser lexemeParser = null;

    private Parser nextParser;

    private LexemeParser(){}

    private void setNextParser(Parser nextParser) {
        this.nextParser = nextParser;
    }

    public static LexemeParser getInstance() {

        if (lexemeParser == null){
            lexemeParser = new LexemeParser();
        }

        return lexemeParser;
    }

    @Override
    public TextComponent parseTextComponent(String content) {
        TextComposite lexeme = new TextComposite();
        lexeme.setComponentType(ComponentType.LEXEME);

        content = content.trim();

        setNextParser(SymbolParser.getInstance());
        lexeme.addTextComponent(nextParser.parseTextComponent(LEXEME_IDENTIFIER));

        String[] parsedContent = content.split(LEXEME_IDENTIFIER);
        for (int arrayIndex = 0; arrayIndex < parsedContent.length; arrayIndex++) {
            setNextParser(WordParser.getInstance());
            String currentContent = parsedContent[arrayIndex];
            TextComponent currentComponent = nextParser.parseTextComponent(currentContent);
            lexeme.addTextComponent(currentComponent);

            setNextParser(SymbolParser.getInstance());

        }

        return lexeme;
    }
}
