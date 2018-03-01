package by.epam.infohandling.text.interpreter;

import by.epam.infohandling.text.interpreter.expressions.*;

public class MathExpressionCalculator {

    private static final char MINUS_OPERATOR = '-';
    private static final char PLUS_OPERATOR = '+';
    private static final char DIVIDE_OPERATOR = '/';
    private static final char MULTIPLY_OPERATOR = '*';
    private static final char OPEN_BRACKET = '(';
    private static final char CLOSED_BRACKET = ')';

    private static final int NEXT_ELEMENT_IDENTIFIER = 1;
    private static final int LAST_ELEMENT_IDENTIFIER = -1;
    private static final int FIRST_ELEMENT_INDEX = 0;

    public int calculateExpression(String mathExpressionContent, int i, int j){
        String iNumber = String.valueOf(i);
        String jNumber = String.valueOf(j);

        mathExpressionContent = mathExpressionContent.replace("i", iNumber);
        mathExpressionContent = mathExpressionContent.replace("j", jNumber);

        Expression number = calculate(mathExpressionContent);
        int result = number.interpret();

        return result;
    }

    private Expression calculate(String mathExpressionContent) {
        int openBracketIndex = mathExpressionContent.indexOf(OPEN_BRACKET);
        if (openBracketIndex != LAST_ELEMENT_IDENTIFIER) {
            mathExpressionContent = subMathExpressionCalculating(openBracketIndex, mathExpressionContent);
        }

        int currentPosition = mathExpressionContent.length() + LAST_ELEMENT_IDENTIFIER;

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
