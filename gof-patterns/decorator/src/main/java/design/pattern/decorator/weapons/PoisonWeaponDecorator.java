package design.pattern.decorator.weapons;

import design.pattern.decorator.Enemy;
import design.pattern.decorator.Player;

import java.util.Random;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class PoisonWeaponDecorator extends WeaponDecorator {
    private int poisonChance;

    public WeaponDecorator initWithWeapon(){
        Random random = new Random(10);
        poisonChance = random.nextInt();
        return this;
    }

    public String getNameSuffix(){
        if( !weapon.isPrefixBeignUsed()){
            weapon.setPrefixBeignUsed(true);
            return "of Plagues";
        } else {
            return weapon.getNameSuffix();
        }
    }

    public String getNamePrefix(){
        if(!weapon.isSuffixBeingUsed()){
            weapon.setSuffixBeingUsed(true);
            return "Plagued";
        } else {
            return weapon.getNamePrefix();
        }
    }

    public int getSellingPrice(){
        return poisonChance + weapon.getSellingPrice();
    }

    @Override public void didHitEnemy(Player player, Enemy enemy, int forDamage) {
        Random random = new Random();
        if( random.nextInt() % 100 < poisonChance ){
            enemy.setPoisoned(true);
        }
        super.didHitEnemy(player, enemy, forDamage);
    }
}
