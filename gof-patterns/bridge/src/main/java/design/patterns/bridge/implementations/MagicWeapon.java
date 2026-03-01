package design.patterns.bridge.implementations;

import design.patterns.bridge.implementations.MagicWeaponImpl;

/**
 * Created by PATEL1 on 12/27/14.
 */
public abstract class MagicWeapon {
    protected MagicWeaponImpl magicWeapon;

    protected MagicWeapon(MagicWeaponImpl magicWeapon) {
        this.magicWeapon = magicWeapon;
    }

    protected abstract void wield();
    protected abstract void swing();
    protected abstract void unwield();
    public MagicWeaponImpl getMagicWeaponImpl(){
        return magicWeapon;
    }
}
