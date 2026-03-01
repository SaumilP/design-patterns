package design.patterns.strategy.implementations;

import design.patterns.strategy.interfaces.OffensiveStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class SeaCommandingStrategy implements OffensiveStrategy {

    private static final Logger Log = LoggerFactory.getLogger(SeaCommandingStrategy.class);

    @Override
    public void attack() {
        Log.debug("Deploying Sea Commanding Strategy with trained navy seals");
    }

    @Override
    public void execute() {
        attack();
    }
}
