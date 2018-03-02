package by.epam.infohandling.util;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.TextComposite;

import java.util.ArrayList;
import java.util.List;

public class ComponentExtractor {

    public static List<TextComponent> extractComponents(TextComponent textComponent, ComponentType componentType) {
        List<TextComponent> components = new ArrayList<>();

        boolean isElementComposite = isObjectComposite(textComponent);
        if (isElementComposite) {
            List<TextComponent> currentComponents = textComponent.getTextComponents();
            for (TextComponent currentComponent : currentComponents) {
                ComponentType currentType = currentComponent.getComponentType();
                if (componentType.equals(currentType)) {
                    components.add(currentComponent);
                } else {
                    components.addAll(extractComponents(currentComponent, componentType));
                }
            }
        }
        return components;
    }

    private static boolean isObjectComposite(TextComponent textComponent) {
        Class currentClass = textComponent.getClass();
        Class compositeClass = TextComposite.class;

        return compositeClass.equals(currentClass);
    }
}
