package by.epam.infohandling.text.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextComposite extends TextComponent {

    private List<TextComponent> textComponents = new ArrayList<TextComponent>();

    public void removeLastTextComponent() {
        if (textComponents.isEmpty()) {
            throw new IllegalArgumentException("Components are empty.");
        }

        int currentSize = textComponents.size();
        int lastComponentIndex = currentSize - 1;

        textComponents.remove(lastComponentIndex);
    }

    public void addTextComponent(TextComponent textComponent) {
        if (textComponent == null) {
            throw new IllegalArgumentException("Incorrect input text component.");
        }

        this.textComponents.add(textComponent);
    }

    @Override
    public List<TextComponent> getTextComponents() {
        return textComponents;
    }

    @Override
    public void setContent(String content) {
        throw new UnsupportedOperationException("setContent operation isn't supported in that class.");
    }

    @Override
    public String getContent() {
        if (this.textComponents.size() != 0) {
            StringBuilder result = new StringBuilder();
            for (TextComponent textComponent : textComponents) {
                result.append(textComponent.getContent());
            }

            return result.toString();
        } else {
            throw new IllegalArgumentException("Data is empty.");
        }
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
        TextComposite that = (TextComposite) object;

        return Objects.equals(textComponents, that.textComponents);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), textComponents);
    }

    @Override
    public String toString() {
        return "TextComposite{" +
                "textComponents=" + textComponents +
                '}';
    }
}
