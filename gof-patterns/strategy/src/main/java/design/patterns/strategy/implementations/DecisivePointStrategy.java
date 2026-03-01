package design.patterns.strategy.implementations;

import design.patterns.strategy.interfaces.DefensiveStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class DecisivePointStrategy implements DefensiveStrategy {
    private static final Logger Log = LoggerFactory.getLogger(DecisivePointStrategy.class);

    @Override
    public void defend() {
        Log.debug("Decisive Point Strategy deployed");
    }

    @Override
    public void execute() {
        defend();
    }
}
