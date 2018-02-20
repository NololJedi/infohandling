package by.epam.infohandling.text.composite;

public class Symbol implements TextComponent {

    private String content;

    public Symbol(String symbol) {
        this.content = symbol;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ComponentType getComponentType() {
        return ComponentType.SYMBOL;
    }

    public void setComponentType(ComponentType componentType) {
        throw new UnsupportedOperationException("Can't change current type.");
    }
}
