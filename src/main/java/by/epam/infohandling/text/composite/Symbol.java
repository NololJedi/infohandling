package by.epam.infohandling.text.composite;

import java.util.List;
import java.util.Objects;

public class Symbol implements TextComponent {

    private String content;
    private SymbolType symbolType;

    public Symbol(String symbol) {
        this.content = symbol;
    }

    public Symbol(String content, SymbolType symbolType) {
        this.content = content;
        this.symbolType = symbolType;
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }

    public void setSymbolType(SymbolType symbolType) {
        if (symbolType == null) {
            throw new IllegalArgumentException("Incorrect symbol type detected.");
        }

        this.symbolType = symbolType;
    }

    @Override
    public List<TextComponent> getTextComponents() {
        throw new UnsupportedOperationException("getTextComponents operation doesn't allow in that class.");
    }

    @Override
    public void addTextComponent(TextComponent textComponent) {
        throw new UnsupportedOperationException("Add operation doesn't allow in that class.");
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.SYMBOL;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Symbol symbol = (Symbol) object;

        if (!content.equals(symbol.content)) {
            return false;
        }
        if (symbolType != symbol.symbolType) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, symbolType);
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "content='" + content + '\'' +
                ", symbolType=" + symbolType +
                '}';
    }
}
