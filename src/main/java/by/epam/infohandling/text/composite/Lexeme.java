package by.epam.infohandling.text.composite;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.TextComposite;

import java.util.List;
import java.util.Objects;

public class Lexeme extends TextComponent {

    private String content;

    public Lexeme() {
    }

    public Lexeme(String content, ComponentType componentType){
        this.content = content;
        this.componentType = componentType;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public List<TextComponent> getTextComponents() {
        throw new UnsupportedOperationException("getTextComponents operation doesn't support in that class.");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        if (!super.equals(object)) return false;
        Lexeme lexeme = (Lexeme) object;

        return Objects.equals(content, lexeme.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), content);
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "content='" + content + '\'' +
                '}';
    }
}
