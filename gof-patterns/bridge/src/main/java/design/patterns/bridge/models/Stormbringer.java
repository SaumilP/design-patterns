package design.patterns.bridge.models;

import design.patterns.bridge.implementations.SoulEatingMagicWeaponImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class Stormbringer extends SoulEatingMagicWeaponImpl {
    private static final Logger Log = LoggerFactory.getLogger(Stormbringer.class);

    @Override
    public void eatSoulImpl() {
        Log.debug("Stormbringer devours enemy's soul");
    }

    @Override
    public void wieldImpl() {
        Log.debug("wielding Stormbringer");
    }

    @Override
    public void unwieldImpl() {
        Log.debug("unwielding Stormbringer");
    }

    @Override
    public void swingImpl() {
        Log.debug("swinging Stormbringer");
    }
}
