package by.epam.infohandling.text.composite;

import java.util.ArrayList;
import java.util.List;

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

    public void setComponentType(ComponentType componentType) {
        if (componentType == null) {
            throw new IllegalArgumentException("Incorrect input component type.");
        }

        this.componentType = componentType;
    }

    public ComponentType getComponentType() {
        return this.componentType;
    }
}
