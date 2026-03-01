package design.patterns.strategy;

import design.patterns.strategy.interfaces.BattlefieldStrategy;
import design.patterns.strategy.interfaces.DefensiveStrategy;
import design.patterns.strategy.interfaces.OffensiveStrategy;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class Army {
    private BattlefieldStrategy battlefieldStrategy;

    public Army(BattlefieldStrategy battlefieldStrategy) {
        this.battlefieldStrategy = battlefieldStrategy;
    }

    public void changeStrategy( OffensiveStrategy strategy ){
        this.battlefieldStrategy = (BattlefieldStrategy) strategy;
    }

    public void changeStrategy( DefensiveStrategy strategy ){
        this.battlefieldStrategy = (BattlefieldStrategy) strategy;
    }

    public void goToBattle(){
        battlefieldStrategy.execute();
    }
}
