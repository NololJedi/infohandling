package by.epam.infohandling.text.parsers;

import org.junit.BeforeClass;
import org.junit.Test;

public class SentenceParserTest {

    private static final String SENTENCE_CONTENT_EXAMPLE = "Dog eat a bone, cat lick (2*2-3) milk.";

    private static SentenceParser sentenceParser;

    @BeforeClass
    public static void setSentenceParser(){
        sentenceParser = SentenceParser.getInstance();
    }

    @Test
    public void shouldSentenceParsingBeSuccessful(){

    }

}
