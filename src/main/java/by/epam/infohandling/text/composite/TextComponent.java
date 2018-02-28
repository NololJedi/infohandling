package by.epam.infohandling.text.composite;

import java.util.List;
import java.util.Objects;

public abstract class TextComponent {

    protected ComponentType componentType;

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public abstract String getContent();

    public abstract List<TextComponent> getTextComponents();

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        TextComponent component = (TextComponent) object;
        return componentType == component.componentType;
    }

    @Override
    public int hashCode() {

        return Objects.hash(componentType);
    }

    @Override
    public String toString() {
        return "TextComponent{" +
                "componentType=" + componentType +
                '}';
    }
}
