package by.epam.infohandling.text.composite;

public abstract class TextComponent {

    protected ComponentType componentType;

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public abstract String getContent();
}
