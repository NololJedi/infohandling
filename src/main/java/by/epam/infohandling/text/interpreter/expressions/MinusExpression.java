package by.epam.infohandling.text.interpreter.expressions;

public class MinusExpression implements Expression {
    private Expression left;
    private Expression right;

    public MinusExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        int leftInterpret = left.interpret();
        int rightInterpret = right.interpret();

        return leftInterpret - rightInterpret;
    }
}
