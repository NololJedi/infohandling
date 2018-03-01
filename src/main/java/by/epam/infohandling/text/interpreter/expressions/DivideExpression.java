package by.epam.infohandling.text.interpreter.expressions;

public class DivideExpression implements Expression {

    private Expression left;
    private Expression right;

    public DivideExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        int leftInterpret = left.interpret();
        int rightInterpret = right.interpret();

        return leftInterpret / rightInterpret;
    }
}
