package design.patterns.strategy.implementations;

import design.patterns.strategy.interfaces.DefensiveStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class FortificationStrategy implements DefensiveStrategy {

    private static final Logger Log = LoggerFactory.getLogger(FortificationStrategy.class);

    @Override
    public void defend() {
        Log.debug("Fortification Defense strategy deployed");
    }

    @Override
    public void execute() {
        defend();
    }
}
