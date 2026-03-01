package design.patterns.visitor;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class Sergeant extends Unit {
    public Sergeant(Unit... children){
        super(children);
    }

    @Override public void accept(UnitVisitor visitor){
        visitor.visitSergeant(this);
        super.accept(visitor);
    }

    @Override public String toString(){
        return "Sergeant";
    }
}
