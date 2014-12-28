package design.patterns.bridge;

import design.patterns.bridge.models.Excalibur;
import design.patterns.bridge.models.Mjollnir;
import design.patterns.bridge.models.Stormbringer;
import design.patterns.bridge.specialisations.BlindingMagicWeapon;
import design.patterns.bridge.specialisations.FlyingMagicWeapon;
import design.patterns.bridge.specialisations.SoulEatingMagicWeapon;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class App {
    public static void main(String[] args) {
        BlindingMagicWeapon blindingMagicWeapon = new BlindingMagicWeapon(
                new Excalibur());
        blindingMagicWeapon.wield();
        blindingMagicWeapon.blind();
        blindingMagicWeapon.swing();
        blindingMagicWeapon.unwield();

        FlyingMagicWeapon flyingMagicWeapon = new FlyingMagicWeapon(
                new Mjollnir());
        flyingMagicWeapon.wield();
        flyingMagicWeapon.fly();
        flyingMagicWeapon.swing();
        flyingMagicWeapon.unwield();

        SoulEatingMagicWeapon soulEatingMagicWeapon = new SoulEatingMagicWeapon(
                new Stormbringer());
        soulEatingMagicWeapon.wield();
        soulEatingMagicWeapon.swing();
        soulEatingMagicWeapon.eatSoul();
        soulEatingMagicWeapon.unwield();

    }
}
