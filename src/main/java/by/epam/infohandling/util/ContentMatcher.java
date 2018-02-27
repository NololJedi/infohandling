package by.epam.infohandling.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentMatcher {

    public static final int LAST_ELEMENT_IDENTIFIER = 1;
    public static final int SYMBOL_LENGTH = 1;

    public static final String SPACE = " ";

    public static final String ALPHABET_PATTERN = "[a-zA-z]";
    public static final String NUMBER_PATTERN = "\\d+";
    public static final String PUNCTUATION_PATTERN = "\\s|\\.|,|!|\\?";
    public static final String WORD_PATTERN = "^[a-zA-Z]+[\\.,!\\?]?";

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
