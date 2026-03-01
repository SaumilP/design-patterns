package design.patterns.bridge.models;

import design.patterns.bridge.implementations.BlindingMagicWeaponImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class Excalibur extends BlindingMagicWeaponImpl {
    private static final Logger Log = LoggerFactory.getLogger(Excalibur.class);

    @Override
    public void blindImpl() {
        Log.debug("bright light streams from Excalibur blinding the enemy");
    }

    @Override
    public void wieldImpl() {
        Log.debug("wielding Excalibur");
    }

    @Override
    public void unwieldImpl() {
        Log.debug("unwielding Excalibur ");
    }

    @Override
    public void swingImpl() {
        Log.debug("swinging Excalibur");
    }
}
