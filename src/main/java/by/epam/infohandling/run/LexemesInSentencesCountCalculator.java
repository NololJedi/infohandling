package by.epam.infohandling.run;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.util.ComponentExtractor;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LexemesInSentencesCountCalculator {

    private static Logger LOGGER = Logger.getLogger(LexemesInSentencesCountCalculator.class);
    private static Map<String, Integer> calculatedSentences = new HashMap<>();

    public void startTask(TextComponent text) {
        if (text == null) {
            throw new IllegalArgumentException("Incorrect text detected.");
        }

        LOGGER.info("Start task - Print all sentences in ascending order of lexemes.");
        List<TextComponent> sentences = ComponentExtractor.extractComponents(text, ComponentType.SENTENCE);

        for (TextComponent sentence : sentences) {
            calculateCountOfLexemes(sentence);
        }

        calculatedSentences.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(LOGGER::info);
    }

    private void calculateCountOfLexemes(TextComponent sentence) {
        List<TextComponent> components = sentence.getTextComponents();
        int lexemesCount = 0;

        for (TextComponent component : components) {
            ComponentType currentType = component.getComponentType();

            if (ComponentType.PUNCTUATION != currentType) {
                lexemesCount++;
            }
        }

        String sentenceContent = sentence.getContent();
        calculatedSentences.put(sentenceContent, lexemesCount);
    }

}
