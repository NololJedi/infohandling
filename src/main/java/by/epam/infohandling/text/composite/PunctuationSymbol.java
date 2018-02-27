package by.epam.infohandling.text.composite;

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
    public String getContent() {
        return content;
    }
}
