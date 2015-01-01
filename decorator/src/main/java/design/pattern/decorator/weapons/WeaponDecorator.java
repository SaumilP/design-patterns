package design.pattern.decorator.weapons;

import design.pattern.decorator.Enemy;
import design.pattern.decorator.Player;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class WeaponDecorator {
    public Weapon weapon;

    public WeaponDecorator initWithWeapon(Weapon weapon){
        weapon = weapon;
        return this;
    }

    public void willHitEnemy(Player player,Enemy enemy, int forDamage){
    }

    public void didHitEnemy(Player player, Enemy enemy, int forDamage){

    }

    public void didDefeatEnemy(Player player, Enemy enemy){

    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
