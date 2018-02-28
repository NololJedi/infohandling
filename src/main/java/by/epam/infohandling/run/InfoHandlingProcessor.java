package by.epam.infohandling.run;

import by.epam.infohandling.exceptions.IncorrectFileException;
import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.TextComposite;
import by.epam.infohandling.text.parsers.LexemeParser;
import by.epam.infohandling.text.parsers.ParagraphParser;
import by.epam.infohandling.text.parsers.SentenceParser;
import by.epam.infohandling.text.parsers.TextParser;
import by.epam.infohandling.util.ComponentExtractor;
import by.epam.infohandling.util.TextFileReader;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class InfoHandlingProcessor {

    private static final Logger LOGGER = Logger.getLogger(InfoHandlingProcessor.class);

    private static final String fileName = "./src/main/resources/text.txt";

    public static void main(String[] args) throws IncorrectFileException {

        String content = new TextFileReader().readTextFromFile("./src/main/resources/text.txt");

        LexemeParser lexemeParser = new LexemeParser();
        SentenceParser sentenceParser = new SentenceParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        sentenceParser.setNextParser(lexemeParser);
        paragraphParser.setNextParser(sentenceParser);

        TextParser textParser = new TextParser();
        textParser.setNextParser(paragraphParser);

        TextComposite text = (TextComposite) textParser.parseTextComponent(content);

       new SentencesWithSameWordsCalculator().startTask(text);
       new LexemesCount().startTask(text);

       List<String> words = new ArrayList<>();
       words.add("will");
       words.add("was");
       words.add("it");

       new WordsOccurrencesCalculator().startTask(text,words);


    }
}
