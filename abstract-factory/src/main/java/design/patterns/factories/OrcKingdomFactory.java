package design.patterns.factories;

import design.patterns.implementations.OrcCastle;
import design.patterns.interfaces.Army;
import design.patterns.interfaces.Castle;
import design.patterns.interfaces.King;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class OrcKingdomFactory implements KingdomFactory {
    @Override
    public Castle createCastle() {
        return new OrcCastle();
    }

    @Override
    public King createKing() {
        return null;
    }

    @Override
    public Army createArmy() {
        return null;
    }
}
