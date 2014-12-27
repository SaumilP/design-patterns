package design.patterns.factories;

import design.patterns.interfaces.Army;
import design.patterns.interfaces.Castle;
import design.patterns.interfaces.King;

/**
 * Created by PATEL1 on 12/27/14.
 */
public interface KingdomFactory {
    Castle createCastle();
    King createKing();
    Army createArmy();
}
