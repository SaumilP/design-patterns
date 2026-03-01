package design.patterns.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class SoldierVisitor implements UnitVisitor {

    public static final Logger Log = LoggerFactory.getLogger( SoldierVisitor.class );

    @Override
    public void visitSoldier(Soldier soldier) {
        Log.debug("Greetings " + soldier);
    }

    @Override
    public void visitSergeant(Sergeant sergeant) {

    }

    @Override
    public void visitCommander(Commander commander) {

    }
}
