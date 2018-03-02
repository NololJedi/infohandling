package by.epam.infohandling.text.interpreter;

import by.epam.infohandling.text.composite.ComponentType;
import by.epam.infohandling.text.composite.TextComponent;
import by.epam.infohandling.text.interpreter.expressions.*;
import by.epam.infohandling.util.ComponentExtractor;
import by.epam.infohandling.util.ContentDeterminant;
import org.apache.log4j.Logger;

import java.util.List;

public class MathExpressionCalculator {

    private static final Logger LOGGER = Logger.getLogger(MathExpressionCalculator.class);

    private static final char MINUS_OPERATOR = '-';
    private static final char PLUS_OPERATOR = '+';
    private static final char DIVIDE_OPERATOR = '/';
    private static final char MULTIPLY_OPERATOR = '*';
    private static final char OPEN_BRACKET = '(';
    private static final char CLOSED_BRACKET = ')';

    private static final int NEXT_ELEMENT_IDENTIFIER = 1;
    private static final int NONE_ELEMENT_IDENTIFIER = -1;
    private static final int FIRST_ELEMENT_INDEX = 0;

    public void calculateExpressions(TextComponent text, int i, int j) {
        if (text == null) {
            throw new IllegalArgumentException("Incorrect text detected.");
        }
        LOGGER.info("Start task - Calculate mathematical expressions in the text.");

        List<TextComponent> expressions = ComponentExtractor.extractComponents(text, ComponentType.MATH_EXPRESSION);

        for (TextComponent expression : expressions) {
            String currentContent = expression.getContent();
            LOGGER.info(String.format("Current expression: %s.", currentContent));

            String resultOfCalculating = calculateExpression(currentContent, i, j);
            LOGGER.info(String.format("Result of calculating: %s.", resultOfCalculating));

            expression.setContent(resultOfCalculating);
        }

    }

    private String calculateExpression(String mathExpressionContent, int i, int j) {
        String iNumber = String.valueOf(i);
        String jNumber = String.valueOf(j);

        boolean isExpressionHasLiteralI = ContentDeterminant
                .determinantContent(mathExpressionContent, ContentDeterminant.MATH_EXPRESSION_WITH_LITERAL_I_PATTERN);
        if (isExpressionHasLiteralI) {
            mathExpressionContent = mathExpressionContent.replace("i", iNumber);
        }

        boolean isExpressionHasLiteralJ = ContentDeterminant
                .determinantContent(mathExpressionContent, ContentDeterminant.MATH_EXPRESSION_WITH_LITERAL_J_PATTERN);
        if (isExpressionHasLiteralJ) {
            mathExpressionContent = mathExpressionContent.replace("j", jNumber);
        }


        Expression number = calculate(mathExpressionContent);
        int result = number.interpret();

        return String.valueOf(result);
    }

    private Expression calculate(String mathExpressionContent) {
        int openBracketIndex = mathExpressionContent.indexOf(OPEN_BRACKET);
        if (openBracketIndex != NONE_ELEMENT_IDENTIFIER) {
            mathExpressionContent = subMathExpressionCalculating(openBracketIndex, mathExpressionContent);
        }

        int currentPosition = mathExpressionContent.length() - NEXT_ELEMENT_IDENTIFIER;

        while (currentPosition > FIRST_ELEMENT_INDEX) {
            char currentCharacter = mathExpressionContent.charAt(currentPosition);
            boolean isCurrentCharacterDigit = Character.isDigit(currentCharacter);

            if (isCurrentCharacterDigit) {
                currentPosition--;
            } else {
                String newContent = mathExpressionContent.substring(FIRST_ELEMENT_INDEX, currentPosition);
                Expression left = calculate(newContent);

                int numberPosition = currentPosition + NEXT_ELEMENT_IDENTIFIER;
                int expressionEnd = mathExpressionContent.length();

                String numberContent = mathExpressionContent.substring(numberPosition, expressionEnd);
                int number = Integer.parseInt(numberContent);
                Expression right = new NumberExpression(number);

                char operator = mathExpressionContent.charAt(currentPosition);

                switch (operator) {
                    case MINUS_OPERATOR: {
                        return new MinusExpression(left, right);
                    }
                    case PLUS_OPERATOR: {
                        return new PlusExpression(left, right);
                    }
                    case MULTIPLY_OPERATOR: {
                        return new MultiplicationExpression(left, right);
                    }
                    case DIVIDE_OPERATOR: {
                        return new DivideExpression(left, right);
                    }
                }
            }
        }
        int result = Integer.parseInt(mathExpressionContent);

        return new NumberExpression(result);
    }

    private String buildReplacementString(String subMathExpression) {
        StringBuilder replaceLineBuilder = new StringBuilder();

        replaceLineBuilder.append(OPEN_BRACKET);
        replaceLineBuilder.append(subMathExpression);
        replaceLineBuilder.append(CLOSED_BRACKET);

        return replaceLineBuilder.toString();
    }

    private String subMathExpressionCalculating(int openBracketIndex, String mathExpressionContent) {
        int closeBracketIndex = mathExpressionContent.lastIndexOf(CLOSED_BRACKET);

        String subMathExpression = mathExpressionContent.substring(openBracketIndex + NEXT_ELEMENT_IDENTIFIER, closeBracketIndex);

        Expression result = calculate(subMathExpression);
        int subMathCalculatingResult = result.interpret();

        String replacementString = buildReplacementString(subMathExpression);
        String resultOfCalculating = String.valueOf(subMathCalculatingResult);

        mathExpressionContent = mathExpressionContent.replace(replacementString, resultOfCalculating);

        return mathExpressionContent;
    }

}
