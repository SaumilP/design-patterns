package design.patterns.interpreter.expressions;

/**
 * AND Expression
 */
public class AndExpression extends Expression {

    private Expression firstExpression = null;
    private Expression secondExpression = null;

    @Override public boolean interpret(String expressionStr) {
        return firstExpression.interpret(expressionStr) &&
               secondExpression.interpret(expressionStr);
    }

    public AndExpression(Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }
}
