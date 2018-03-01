package by.epam.infohandling.run;

import by.epam.infohandling.exceptions.IncorrectFileException;
import by.epam.infohandling.text.interpreter.MathExpressionCalculator;
import org.apache.log4j.Logger;

public class InfoHandlingProcessor {

    private static final Logger LOGGER = Logger.getLogger(InfoHandlingProcessor.class);

    private static final String fileName = "./src/main/resources/text.txt";

    public static void main(String[] args) throws IncorrectFileException {

//        String content = new TextFileReader().readTextFromFile("./src/main/resources/text.txt");
//
//        LexemeParser lexemeParser = new LexemeParser();
//        SentenceParser sentenceParser = new SentenceParser();
//        ParagraphParser paragraphParser = new ParagraphParser();
//        sentenceParser.setNextParser(lexemeParser);
//        paragraphParser.setNextParser(sentenceParser);
//
//        TextParser textParser = new TextParser();
//        textParser.setNextParser(paragraphParser);
//
//        TextComposite text = (TextComposite) textParser.parseTextComponent(content);
//
//       new SentencesWithSameWordsCalculator().startTask(text);
//       new LexemesCount().startTask(text);
//
//       List<String> words = new ArrayList<>();
//       words.add("will");
//       words.add("was");
//       words.add("it");
//
//       new WordsOccurrencesCalculator().startTask(text,words);
//
//       List<TextComponent> components = ComponentExtractor.extractComponents(text,ComponentType.MATH_EXPRESSION);
//
//        for (TextComponent component : components) {
//            System.out.println(component.getContent());
//        }

        String math = "2*(23+2)+1";
        MathExpressionCalculator m = new MathExpressionCalculator();
        System.out.println(m.calculateExpression(math, 3, 2));
    }
}
