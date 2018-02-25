package by.epam.infohandling.text.composite.lexeme;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.TextComposite;

import java.util.Objects;

public class Lexeme extends TextComposite {

    private LexemeType lexemeType;

    public Lexeme() {
        super.setComponentType(ComponentType.LEXEME);
    }

    public LexemeType getLexemeType() {
        return lexemeType;
    }

    public void setLexemeType(LexemeType lexemeType) {
        this.lexemeType = lexemeType;
    }

    @Override
    public void setComponentType(ComponentType componentType) {
        throw new UnsupportedOperationException("Unsupported operation detected.");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }

        Lexeme lexeme = (Lexeme) object;

        if (lexemeType != lexeme.lexemeType) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash += Objects.hash(lexemeType);

        return hash;
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "lexemeType=" + lexemeType +
                '}';
    }
}
