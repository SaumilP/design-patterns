package design.patterns.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class CommanderVisitor implements UnitVisitor {

    private static final Logger Log = LoggerFactory.getLogger(CommanderVisitor.class);

    @Override
    public void visitSoldier(Soldier soldier) {
    }

    @Override
    public void visitSergeant(Sergeant sergeant) {
    }

    @Override
    public void visitCommander(Commander commander) {
        Log.debug("Good Morning Commander " + commander);
    }
}
