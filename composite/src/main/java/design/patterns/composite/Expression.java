package design.patterns.composite;

/**
 * Created by PATEL1 on 1/1/15.
 */
public interface Expression {
    Expression add(Expression expression);
    Expression set(String name, Boolean value);
    Boolean evaluate();
}
