package by.epam.infohandling.util;

import by.epam.infohandling.text.composite.ComponentType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentDeterminant {

    public static final int LAST_ELEMENT_IDENTIFIER = 1;
    public static final int SYMBOL_LENGTH = 1;
    public static final String SPACE = " ";

    public static final String ALPHABET_PATTERN = "[a-zA-z]{1}";
    public static final String NUMBER_PATTERN = "\\d+";
    public static final String PUNCTUATION_PATTERN = "\\s|\\.|,|!|\\?|-";
    public static final String WORD_PATTERN = "[a-zA-Z\\-]{2,}[\\.,!\\?]?";
    public static final String MATH_PATTERN = "((\\d+|[ij])[(\\\\d+\\+\\-\\*/\\\\d+|(ij)]*)+";
    public static final String SENTENCE_PATTERN = "[\\p{Upper}+\\-(](.(?!\\.))*..";
    public static final String PARAGRAPH_PATTERN = "\\t?[\\p{Upper}+\\-(](.(?!\\r?\\n\\r?\\n))*.";

    private static final Map<String, ComponentType> MATCH_MAP = new HashMap<>();

    static {
        MATCH_MAP.put(ALPHABET_PATTERN, ComponentType.ALPHABET_SYMBOL);
        MATCH_MAP.put(NUMBER_PATTERN, ComponentType.NUMBER);
        MATCH_MAP.put(WORD_PATTERN, ComponentType.WORD);
        MATCH_MAP.put(MATH_PATTERN, ComponentType.MATH_EXPRESSION);
    }

    public static boolean determinantContent(String content, String pattern) {
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

    public static ComponentType determinantType(String content) {
        ComponentType type = null;
        Set<Map.Entry<String, ComponentType>> mapToSet = MATCH_MAP.entrySet();

        for (Map.Entry<String, ComponentType> currentType : mapToSet) {
            String pattern = currentType.getKey();
            boolean isTypeDetected = determinantContent(content, pattern);

            if (isTypeDetected) {
                type = currentType.getValue();
                break;
            }
        }

        if (type != null) {
            return type;
        } else {
            return ComponentType.UNKNOWN;
        }
    }
}
