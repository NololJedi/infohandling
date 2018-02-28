package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.PunctuationSymbol;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.Lexeme;
import by.epam.infohandling.util.ContentDeterminant;

import static by.epam.infohandling.util.ContentDeterminant.*;

public class LexemeParser extends Parser {

    @Override
    public void setNextParser(Parser nextParser) {
        throw new IllegalArgumentException("Final parser of chain. Operation not supported.");
    }

    @Override
    public TextComponent parseTextComponent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Incorrect content.");
        }

        if (content.length() == SYMBOL_LENGTH) {
            boolean isContentPunctuation = ContentDeterminant.matchContent(content,PUNCTUATION_PATTERN);
            if (isContentPunctuation){
                PunctuationSymbol punctuationSymbol = new PunctuationSymbol(content);

                return punctuationSymbol;
            }
        }

        Lexeme lexeme = new Lexeme();
        lexeme.setContent(content);

        ComponentType lexemeType = ContentDeterminant.determinantType(content);
        lexeme.setComponentType(lexemeType);

        return lexeme;
    }

}
