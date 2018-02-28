package by.epam.infohandling.run;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.util.ComponentExtractor;
import org.apache.log4j.Logger;

import java.util.List;

public class SentencesWithSameWordsCalculator {

    private static final Logger LOGGER = Logger.getLogger(SentencesWithSameWordsCalculator.class);

    public void startTask(TextComponent text) {
        if (text == null){
            throw new IllegalArgumentException("Incorrect text detected.");
        }

        LOGGER.info("Start run - Calculate count of sentences with same words.");

        List<TextComponent> sentences = ComponentExtractor.extractComponents(text, ComponentType.SENTENCE);
        int currentCountOfSentences = sentences.size();
        LOGGER.info(String.format("Current count os sentences - %d.", currentCountOfSentences));

        int countOfSentencesWithSameWords = 0;

        for (TextComponent sentence : sentences) {
            LOGGER.info(String.format("Checking sentence - %s", sentence.getContent()));

            boolean isSentenceValid = isSentenceWithSameWords(sentence);
            if (isSentenceValid){
                LOGGER.info("Sentence has same words.");
                countOfSentencesWithSameWords++;
            } else {
                LOGGER.info("Sentence hasn't same words.");
            }
        }

        if (countOfSentencesWithSameWords == 0){
            LOGGER.info("No sentences were found.");
        } else {
            String line = countOfSentencesWithSameWords == 1 ? "sentence" : "sentences";
            LOGGER.info(String.format("Text has %d %s with same words.", countOfSentencesWithSameWords, line));
        }
    }

    private boolean isSentenceWithSameWords(TextComponent sentence){
        List<TextComponent> words = ComponentExtractor.extractComponents(sentence, ComponentType.WORD);

        for (int listIndex = 0; listIndex < words.size()-1; listIndex++) {
            TextComponent word = words.get(listIndex);

            for (int innerListIndex = listIndex+1; innerListIndex < words.size(); innerListIndex++) {
                TextComponent currentWord = words.get(innerListIndex);
                if (word.equals(currentWord)){
                    return true;
                }
            }
        }

        return false;
    }
}
