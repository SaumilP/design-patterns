package design.patterns.bridge.specialisations;

import design.patterns.bridge.implementations.MagicWeapon;
import design.patterns.bridge.implementations.SoulEatingMagicWeaponImpl;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class SoulEatingMagicWeapon extends MagicWeapon {

    public SoulEatingMagicWeapon(SoulEatingMagicWeaponImpl impl) {
        super(impl);
    }

    @Override
    public SoulEatingMagicWeaponImpl getMagicWeaponImpl() {
        return (SoulEatingMagicWeaponImpl)magicWeapon;
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

    public void eatSoul(){
        getMagicWeaponImpl().eatSoulImpl();
    }
}
