package design.patterns.visitor;

/**
 * Created by PATEL1 on 12/28/14.
 */
public interface UnitVisitor {
    void visitSoldier(Soldier soldier);
    void visitSergeant(Sergeant sergeant);
    void visitCommander(Commander commander);
}

