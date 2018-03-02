package by.epam.infohandling.run;

import by.epam.infohandling.exceptions.IncorrectFileException;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.interpreter.MathExpressionCalculator;
import by.epam.infohandling.text.parsers.LexemeParser;
import by.epam.infohandling.text.parsers.ParagraphParser;
import by.epam.infohandling.text.parsers.SentenceParser;
import by.epam.infohandling.text.parsers.TextParser;
import by.epam.infohandling.util.TextFileReader;
import by.epam.infohandling.util.TextRestorer;
import org.apache.log4j.Logger;

import java.util.List;

public class InfoHandlingProcessor {

    private static final Logger LOGGER = Logger.getLogger(InfoHandlingProcessor.class);

    private static final String fileName = "./src/main/resources/text.txt";

    public TextComponent getParsedText() {
        TextFileReader textFileReader = new TextFileReader();
        try {
            String currentText = textFileReader.readTextFromFile(fileName);
            LOGGER.info(String.format("Text from file: \n%s", currentText));

            LexemeParser lexemeParser = new LexemeParser();
            SentenceParser sentenceParser = new SentenceParser();
            ParagraphParser paragraphParser = new ParagraphParser();
            TextParser textParser = new TextParser();

            sentenceParser.setNextParser(lexemeParser);
            paragraphParser.setNextParser(sentenceParser);
            textParser.setNextParser(paragraphParser);

            TextComponent text = textParser.parseTextComponent(currentText);

            return text;
        } catch (IncorrectFileException exception) {
            LOGGER.warn("Incorrect file.", exception);

            return null;
        }
    }

    public void processTasks(TextComponent text, List<String> words, int i, int j) {

        SentencesWithSameWordsCalculator sentencesWithSameWordsCalculator = new SentencesWithSameWordsCalculator();
        sentencesWithSameWordsCalculator.startTask(text);

        LexemesInSentencesCountCalculator lexemesInSentencesCountCalculator = new LexemesInSentencesCountCalculator();
        lexemesInSentencesCountCalculator.startTask(text);

        WordsOccurrencesCountCalculator wordsOccurrencesCountCalculator = new WordsOccurrencesCountCalculator();
        wordsOccurrencesCountCalculator.startTask(text, words);

        MathExpressionCalculator mathExpressionCalculator = new MathExpressionCalculator();
        mathExpressionCalculator.calculateExpressions(text, i, j);

        TextRestorer textRestorer = new TextRestorer();
        textRestorer.restoreText(text);
    }
}
