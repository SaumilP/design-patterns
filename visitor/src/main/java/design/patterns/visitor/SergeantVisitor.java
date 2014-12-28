package design.patterns.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class SergeantVisitor implements UnitVisitor {

    private static final Logger Log = LoggerFactory.getLogger(SergeantVisitor.class);


    @Override
    public void visitSoldier(Soldier soldier) {
    }

    @Override
    public void visitSergeant(Sergeant sergeant) {
        Log.debug("visiting sergeant");
    }

    @Override
    public void visitCommander(Commander commander) {
    }
}
