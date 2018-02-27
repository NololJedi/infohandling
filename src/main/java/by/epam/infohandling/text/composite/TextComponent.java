package by.epam.infohandling.text.composite;

import java.util.List;

public interface TextComponent {

    List<TextComponent> getTextComponents();

    void addTextComponent(TextComponent textComponent);

    String getContent();

    ComponentType getComponentType();

}
