package design.patterns.interpreter.expressions;

/**
 * Abstract Expression class denoting methods for expression interpreters
 */
public abstract class Expression {
    abstract public boolean interpret(String expressionStr);
}
