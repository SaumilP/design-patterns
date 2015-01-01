package design.pattern.decorator.weapons;

import java.util.Random;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class CriticalDamageWeaponDecorator extends WeaponDecorator {
    private int extraCriticalDamage;

    public WeaponDecorator initWithWeapon(){
        Random random = new Random(75);
        extraCriticalDamage = random.nextInt(25);
        return this;
    }

    public String getNameSuffix(){
        if( !weapon.isPrefixBeignUsed()){
            weapon.setPrefixBeignUsed(true);
            return "of the Beast";
        } else {
            return weapon.getNameSuffix();
        }
    }

    public String getNamePrefix(){
        if(!weapon.isSuffixBeingUsed()){
            weapon.setSuffixBeingUsed(true);
            return "Beastly";
        } else {
            return weapon.getNamePrefix();
        }
    }

    public int getCriticalPercentBonusDamage(){
        return extraCriticalDamage + weapon.getCritPercentBonusDamage();
    }

    public int getSellingPrice(){
        return (int) (extraCriticalDamage*0.1 + weapon.getSellingPrice());
    }
}
