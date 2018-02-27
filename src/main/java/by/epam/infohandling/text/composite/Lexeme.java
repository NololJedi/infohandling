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

}
