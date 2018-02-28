package by.epam.infohandling.run;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.util.ComponentExtractor;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsOccurrencesCalculator {

    private static final Logger LOGGER = Logger.getLogger(WordsOccurrencesCalculator.class);
    private static Map<String, Integer> wordsOccurrences = new HashMap<>();

    public void startTask(TextComponent text, List<String> words){
        if (text == null) {
            throw new IllegalArgumentException("Incorrect text detected.");
        }
        if (words == null || words.isEmpty()){
            throw new IllegalArgumentException("Incorrect words detected.");
        }

        LOGGER.info("Start task - Calculating count of words entire in paragraphs and sorting them.");
        List<TextComponent> wordsFromText = ComponentExtractor.extractComponents(text, ComponentType.WORD);

        for (String word : words) {
            calculateWordOccurrences(word,wordsFromText);
        }

        wordsOccurrences.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(LOGGER::info);
    }

    private void calculateWordOccurrences(String word, List<TextComponent> wordsFromText){
        int countOfOccurrences = 0;

        for (TextComponent wordFromText : wordsFromText) {
            String currentContent = wordFromText.getContent();

            if (word.equalsIgnoreCase(currentContent)){
                countOfOccurrences++;
            }
        }

        wordsOccurrences.put(word,countOfOccurrences);
    }

}
