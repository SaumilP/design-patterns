package design.patterns.bridge.specialisations;

import design.patterns.bridge.implementations.BlindingMagicWeaponImpl;
import design.patterns.bridge.implementations.MagicWeapon;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class BlindingMagicWeapon extends MagicWeapon {

    public BlindingMagicWeapon(BlindingMagicWeaponImpl impl) {
        super(impl);
    }

    @Override
    public BlindingMagicWeaponImpl getMagicWeaponImpl(){
        return (BlindingMagicWeaponImpl)magicWeapon;
    }

    @Override
    public void wield() {
        getMagicWeaponImpl().wieldImpl();
    }

    @Override
    public void swing() {
        getMagicWeaponImpl().swingImpl();
    }

    @Override
    public void unwield() {
        getMagicWeaponImpl().unwieldImpl();
    }

    public void blind(){
        getMagicWeaponImpl().blindImpl();
    }
}
