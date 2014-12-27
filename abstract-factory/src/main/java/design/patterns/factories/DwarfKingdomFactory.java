package design.patterns.factories;

import design.patterns.implementations.DwarfArmy;
import design.patterns.implementations.DwarfCastle;
import design.patterns.implementations.DwarfKing;
import design.patterns.interfaces.Army;
import design.patterns.interfaces.Castle;
import design.patterns.interfaces.King;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class DwarfKingdomFactory implements KingdomFactory {
    @Override
    public Castle createCastle() {
        return new DwarfCastle();
    }

    @Override
    public King createKing() {
        return new DwarfKing();
    }

    @Override
    public Army createArmy() {
        return new DwarfArmy();
    }
}
