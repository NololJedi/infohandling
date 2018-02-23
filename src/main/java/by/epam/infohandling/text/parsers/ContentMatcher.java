package by.epam.infohandling.text.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentMatcher {

    public static final int SYMBOL_LENGTH = 1;

    public static final String SYMBOL_ALPHABET_PATTERN = "[a-zA-z]";
    public static final String SYMBOL_NUMBER_PATTERN = "[0-9]";
    public static final String SYMBOL_MATH_PATTERN = "-|\\(|\\)|\\*|[+]|/";
    public static final String SYMBOL_PUNCTUATION_PATTERN = "\\s|\\.|,|!|\\?";

    public static final String LEXEME_IDENTIFIER = " ";
    public static final String LEXEME_PATTERN = "\\s.+\\s";

    public static boolean contentMatch(String content, String pattern) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Incorrect content was detected.");
        }
        if (pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("Incorrect pattern was detected.");
        }

        Pattern currentPattern = Pattern.compile(pattern);
        Matcher matcher = currentPattern.matcher(content);

        return matcher.matches();
    }
}
