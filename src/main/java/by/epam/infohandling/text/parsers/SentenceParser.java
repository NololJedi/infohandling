package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.TextComposite;

public class SentenceParser implements Parser {

    private static SentenceParser sentenceParser = null;

    private Parser nextParser;

    private SentenceParser() {
    }

    public static SentenceParser getInstance() {

        if (sentenceParser == null) {
            sentenceParser = new SentenceParser();
        }

        return sentenceParser;
    }

    @Override
    public TextComponent parseTextComponent(String content) {
        TextComposite sentence = new TextComposite();
        sentence.setComponentType(ComponentType.SENTENCE);

        setNextParser(LexemeParser.getInstance());
        String firstWordContent = wordInjector(content);
        TextComponent firstWord = nextParser.parseTextComponent(firstWordContent);
        sentence.addTextComponent(firstWord);



        return sentence;
    }

    private String wordInjector(String content){
        char space = ' ';
        int lineStart = 0;
        int firstSpaceIndex = content.indexOf(space);

        String firstWord = content.substring(lineStart,firstSpaceIndex);

        return firstWord;
    }

    private void setNextParser(Parser nextParser) {
        this.nextParser = nextParser;
    }

}
