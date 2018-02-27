package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.TextComponent;

public abstract class Parser {

    protected Parser nextParser;

    public abstract TextComponent parseTextComponent(String content);

    public void setNextParser(Parser nextParser) {
        this.nextParser = nextParser;
    }
}
