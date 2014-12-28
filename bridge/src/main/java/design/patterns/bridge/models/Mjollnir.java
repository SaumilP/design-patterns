package design.patterns.bridge.models;

import design.patterns.bridge.implementations.FlyingMagicWeaponImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class Mjollnir extends FlyingMagicWeaponImpl{
    private static final Logger Log = LoggerFactory.getLogger(Mjollnir.class);

    @Override
    public void flyImpl() {
        Log.debug("Mjollnir hits the enemy in the air and returning back to the owner's hand");
    }

    @Override
    public void wieldImpl() {
        Log.debug("wielding Mjollnir");
    }

    @Override
    public void unwieldImpl() {
        Log.debug("unwielding Mjollnir");
    }

    @Override
    public void swingImpl() {
        Log.debug("swiging Mjollnir");
    }
}
