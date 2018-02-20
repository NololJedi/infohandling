package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.TextComponent;

public interface Parser {

    TextComponent parseTextComponent(String content);

}
