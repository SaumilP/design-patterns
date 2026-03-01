package design.patterns.visitor;

/**
 * Created by PATEL1 on 12/28/14.
 */
public abstract class Unit {
    private Unit[] children;

    public Unit(Unit... children){
        this.children = children;
    }

    public void accept(UnitVisitor visitor){
        for (Unit child : children) {
            child.accept(visitor);
        }
    }
}
