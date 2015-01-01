package design.pattern.decorator.weapons;

import design.pattern.decorator.Enemy;
import design.pattern.decorator.Player;

import java.util.Random;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class LifeLeechWeaponDecorator extends WeaponDecorator {
    private int percentLifeStolenOnHit;

    public WeaponDecorator initWithWeapon(){
        Random random = new Random(15);
        percentLifeStolenOnHit = random.nextInt();
        return this;
    }

    public String getNameSuffix(){
        if( !weapon.isPrefixBeignUsed()){
            weapon.setPrefixBeignUsed(true);
            return "of Fangs";
        } else {
            return weapon.getNameSuffix();
        }
    }

    public String getNamePrefix(){
        if(!weapon.isSuffixBeingUsed()){
            weapon.setSuffixBeingUsed(true);
            return "Fanged";
        } else {
            return weapon.getNamePrefix();
        }
    }

    public int getSellingPrice(){
        return (int) (percentLifeStolenOnHit/2 + weapon.getSellingPrice());
    }

    @Override public void didHitEnemy(Player player, Enemy enemy, int damage) {
        int damageToHeal = (int)Math.round( damage * ( percentLifeStolenOnHit * 0.01));
        int intDamageToHeal = player.getCurrentHealth() + damageToHeal;
        player.setCurrentHealth( intDamageToHeal );
        super.didHitEnemy(player, enemy, damage);
    }
}
