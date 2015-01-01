package design.patterns.interpreter.expressions;

/**
 * OR expression
 */
public class OrExpression extends Expression {

    private Expression firstExpression = null;
    private Expression secondExpression = null;

    @Override public boolean interpret(String expressionStr) {
        return firstExpression.interpret(expressionStr) ||
               secondExpression.interpret(expressionStr);
    }

    public OrExpression(Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }
}
