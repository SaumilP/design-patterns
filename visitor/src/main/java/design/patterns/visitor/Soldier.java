package design.patterns.visitor;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class Soldier extends Unit{
    public Soldier(Unit... children){
        super(children);
    }

    @Override public void accept(UnitVisitor visitor){
        visitor.visitSoldier( this );
        super.accept(visitor);
    }

    @Override public String toString(){
        return "Soldier";
    }
}
