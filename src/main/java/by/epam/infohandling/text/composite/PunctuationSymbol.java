package by.epam.infohandling.text.composite;

import java.util.List;
import java.util.Objects;

public class PunctuationSymbol extends TextComponent {

    private String content;

    public PunctuationSymbol(String content) {
        this.content = content;
        this.componentType = ComponentType.PUNCTUATION;
    }

    @Override
    public void setComponentType(ComponentType componentType) {
        throw new UnsupportedOperationException("setComponentType doesn't support in that class.");
    }

    @Override
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
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        PunctuationSymbol that = (PunctuationSymbol) object;

        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), content);
    }

    @Override
    public String toString() {
        return "PunctuationSymbol{" +
                "content='" + content + '\'' +
                '}';
    }
}
