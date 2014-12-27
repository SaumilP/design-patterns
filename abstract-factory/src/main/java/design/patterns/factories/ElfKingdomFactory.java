package design.patterns.factories;

import design.patterns.adapter.implementations.ElfArmy;
import design.patterns.adapter.implementations.ElfCastle;
import design.patterns.adapter.implementations.ElfKing;
import design.patterns.adapter.interfaces.Army;
import design.patterns.adapter.interfaces.Castle;
import design.patterns.adapter.interfaces.King;

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
