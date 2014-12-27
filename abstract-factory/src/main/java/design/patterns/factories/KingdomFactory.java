package design.patterns.factories;

import design.patterns.adapter.interfaces.Army;
import design.patterns.adapter.interfaces.Castle;
import design.patterns.adapter.interfaces.King;

/**
 * Created by PATEL1 on 12/27/14.
 */
public interface KingdomFactory {
    Castle createCastle();
    King createKing();
    Army createArmy();
}
