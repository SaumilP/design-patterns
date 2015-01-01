package design.patterns.composite;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class Variable implements Expression {
    private String name;
    private Boolean value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getValue() {
        return value;
    }

    public Variable(String name) {
        this.name = name;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override public Expression add(Expression expression) {
        return this;
    }

    @Override public Expression set(String name, Boolean value) {
        if( name != null && name.equalsIgnoreCase(name) ){
            this.value = value;
        }
        return this;
    }

    @Override public Boolean evaluate() {
        return value;
    }
}
