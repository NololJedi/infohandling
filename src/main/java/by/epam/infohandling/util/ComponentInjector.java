package by.epam.infohandling.util;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.TextComposite;

import java.util.ArrayList;
import java.util.List;

public class ComponentInjector {

    public static List<TextComponent> getComponentsByType(TextComponent textComponent, ComponentType componentType) {
        List<TextComponent> components = new ArrayList<>();

        List<TextComponent> currentComponents = textComponent.getTextComponents();
        for (TextComponent currentComponent : currentComponents) {
            ComponentType currentType = currentComponent.getComponentType();
            if (componentType.equals(currentType)) {
                components.add(currentComponent);
            } else {
                boolean isElementComposite = isElementComposite(currentComponent);
                if (isElementComposite) {
                    components.addAll(getComponentsByType(currentComponent, componentType));
                }
            }
        }

        return components;
    }

    private static boolean isElementComposite(TextComponent textComponent) {
        Class currentClass = textComponent.getClass();

        return TextComposite.class.equals(currentClass);
    }
}
