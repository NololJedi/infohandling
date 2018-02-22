package by.epam.infohandling.text.parsers;

import by.epam.infohandling.text.composite.Symbol;
import by.epam.infohandling.text.composite.SymbolType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.composite.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymbolParser implements Parser {

    private static final String ALPHABET_PATTERN = "[a-zA-z]";
    private static final String NUMBER_PATTERN = "[0-9]";
    private static final String MATH_PATTERN = "-|\\(|\\)|\\*|[+]|/";
    private static final String PUNCTUATION_PATTERN = "\\s|\\.|,|!|\\?";

    public static final int SYMBOL_LENGTH = 1;

    private static SymbolParser symbolParser = null;

    private SymbolParser(){}

    public static SymbolParser getInstance(){
        if (symbolParser == null) {
            symbolParser = new SymbolParser();
        }

        return symbolParser;
    }
    @Override
    public TextComponent parseTextComponent(String content) {
        if (content == null || content.isEmpty()){
            throw new IllegalArgumentException("Incorrect content.");
        }

        if (content.length() == SYMBOL_LENGTH){
            Symbol symbol = new Symbol(content);
            SymbolType symbolType = identifySymbolType(content);
            symbol.setSymbolType(symbolType);

            return symbol;
        } else {
            TextComposite symbols = new TextComposite();
            char[] characters = content.toCharArray();

            for (char currentCharacter : characters) {
                String currentContent = String.valueOf(currentCharacter);

                TextComponent symbol = parseTextComponent(currentContent);
                symbols.addTextComponent(symbol);
            }

            return symbols;
        }
    }

    private SymbolType identifySymbolType(String content){
        boolean alphabetMatch = patternMatch(content,ALPHABET_PATTERN);
        if (alphabetMatch){
            return SymbolType.ALPHABET;
        }

        boolean mathMatch = patternMatch(content, MATH_PATTERN);
        if (mathMatch){
            return SymbolType.MATH;
        }

        boolean punctuationMatch = patternMatch(content, PUNCTUATION_PATTERN);
        if (punctuationMatch){
            return SymbolType.PUNCTUATION;
        }

        boolean numberMatch = patternMatch(content,NUMBER_PATTERN);
        if (numberMatch){
            return SymbolType.NUMBER;
        }

        throw new IllegalArgumentException("Content can't be identified.");
    }

    private boolean patternMatch(String content, String symbolTypePattern){
        Pattern pattern = Pattern.compile(symbolTypePattern);
        Matcher matcher = pattern.matcher(content);

        return matcher.matches();
    }
}
