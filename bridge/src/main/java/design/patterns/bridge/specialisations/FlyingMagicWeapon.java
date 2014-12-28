package design.patterns.bridge.specialisations;

import design.patterns.bridge.implementations.FlyingMagicWeaponImpl;
import design.patterns.bridge.implementations.MagicWeapon;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class FlyingMagicWeapon extends MagicWeapon {

    public FlyingMagicWeapon(FlyingMagicWeaponImpl impl) {
        super(impl);
    }

    @Override
    public FlyingMagicWeaponImpl getMagicWeaponImpl() {
        return (FlyingMagicWeaponImpl)magicWeapon;
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

    public void fly(){
        getMagicWeaponImpl().flyImpl();
    }
}
