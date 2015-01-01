package design.patterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PATEL1 on 1/1/15.
 */
public abstract class Composite implements Expression {

    public List<Expression> children;
    public abstract Boolean evaluate();

    public Composite() {
        children = new ArrayList<>();
    }

    @Override public Expression add(Expression expression) {
        if ( expression != null ){
            children.add( expression );
        }
        return this;
    }

    @Override public Expression set(String name, Boolean value) {
        for (Expression child : children) {
            child.set(name, value);
        }
        return this;
    }

    public Boolean hasChildren() {
        return !children.isEmpty();
    }
}
