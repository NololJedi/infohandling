package by.epam.infohandling.text.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextComposite implements TextComponent {

    private List<TextComponent> textComponents = new ArrayList<TextComponent>();
    private ComponentType componentType;

    public List<TextComponent> getTextComponentsByType(ComponentType inputType) {
        if (inputType == null) {
            throw new IllegalArgumentException("Incorrect input component inputType.");
        }

        List<TextComponent> components = new ArrayList<TextComponent>();
        for (TextComponent currentComponent : textComponents) {
            ComponentType currentComponentType = currentComponent.getComponentType();
            if (currentComponentType == inputType) {
                components.add(currentComponent);
            }
        }

        if (components.size() != 0) {
            return components;
        } else {
            throw new IllegalArgumentException("Such type components were not found.");
        }
    }

    public void addTextComponent(TextComponent textComponent) {
        if (textComponent == null) {
            throw new IllegalArgumentException("Incorrect input text component.");
        }

        this.textComponents.add(textComponent);
    }

    public void removeTextComponent(TextComponent textComponent) {
        if (textComponent == null) {
            throw new IllegalArgumentException("Incorrect input text component.");
        }

        if (this.textComponents.contains(textComponent)) {
            this.textComponents.remove(textComponent);
        } else {
            throw new IllegalArgumentException("Text component doesn't exist in current list of components.");
        }
    }

    public void setComponentType(ComponentType componentType) {
        if (componentType == null) {
            throw new IllegalArgumentException("Incorrect input component type.");
        }

        this.componentType = componentType;
    }

    public List<TextComponent> getTextComponents() {
        return textComponents;
    }

    @Override
    public ComponentType getComponentType() {
        return this.componentType;
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
        TextComposite that = (TextComposite) object;

        if (!textComponents.equals(that.textComponents)) {
            return false;
        }
        if (componentType != that.componentType) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(textComponents, componentType);
    }

    @Override
    public String toString() {
        return "TextComposite{" +
                "textComponents=" + textComponents +
                ", componentType=" + componentType +
                '}';
    }
}
