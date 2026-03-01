package design.patterns.factories;

import design.patterns.adapter.implementations.DwarfArmy;
import design.patterns.adapter.implementations.DwarfCastle;
import design.patterns.adapter.implementations.DwarfKing;
import design.patterns.adapter.interfaces.Army;
import design.patterns.adapter.interfaces.Castle;
import design.patterns.adapter.interfaces.King;

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
