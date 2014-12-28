package design.patterns.strategy.implementations;

import design.patterns.strategy.interfaces.OffensiveStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class AirSuperiorityStrategy implements OffensiveStrategy {

    private static final Logger Log = LoggerFactory.getLogger( AirSuperiorityStrategy.class );

    @Override
    public void attack() {
        Log.debug("Air strike strategy deployed");
    }

    @Override
    public void execute() {
        attack();
    }
}
