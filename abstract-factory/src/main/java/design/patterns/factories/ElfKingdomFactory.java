package design.patterns.factories;

import design.patterns.implementations.ElfArmy;
import design.patterns.implementations.ElfCastle;
import design.patterns.implementations.ElfKing;
import design.patterns.interfaces.Army;
import design.patterns.interfaces.Castle;
import design.patterns.interfaces.King;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class ElfKingdomFactory implements KingdomFactory {
    @Override
    public Castle createCastle() {
        return new ElfCastle();
    }

    @Override
    public King createKing() {
        return new ElfKing();
    }

    @Override
    public Army createArmy() {
        return new ElfArmy();
    }
}
