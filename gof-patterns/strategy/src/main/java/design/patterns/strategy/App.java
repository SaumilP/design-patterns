package design.patterns.strategy;

import design.patterns.strategy.implementations.AirSuperiorityStrategy;
import design.patterns.strategy.implementations.FortificationStrategy;
import design.patterns.strategy.implementations.SeaCommandingStrategy;
import design.patterns.strategy.interfaces.BattlefieldStrategy;
import design.patterns.strategy.interfaces.OffensiveStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class App {
    private static final Logger Log = LoggerFactory.getLogger( App.class );

    public static void main(String[] args){
        Log.debug("Enemy army spotted ahead!!!");
        Army army = new Army((BattlefieldStrategy) new AirSuperiorityStrategy());
        army.goToBattle();

        Log.debug("Enemy approaching from sea!!!");
        army.changeStrategy( new SeaCommandingStrategy() );
        army.goToBattle();

        Log.debug("Enemy planes spotted, defene required !!!");
        army.changeStrategy( new FortificationStrategy() );
        army.goToBattle();
    }
}
