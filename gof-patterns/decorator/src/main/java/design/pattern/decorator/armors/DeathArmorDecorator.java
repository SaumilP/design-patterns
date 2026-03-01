package design.pattern.decorator.armors;

import java.util.Random;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class DeathArmorDecorator extends ArmorDecorator{
    private int chanceOfDeath;

    public ArmorDecorator initWithArmor(){
        Random random = new Random(15);
        chanceOfDeath = random.nextInt();
        return this;
    }

    public int getSellingPrice(){
        return chanceOfDeath*2 + armor.getSellingPrice();
    }

    public String getNameSuffix(){
        if( !armor.isPrefixBeignUsed()){
            armor.setPrefixBeignUsed(true);
            return "of Reaping";
        } else {
            return armor.getNameSuffix();
        }
    }

    public String getNamePrefix(){
        if(!armor.isSuffixBeingUsed()){
            armor.setSuffixBeingUsed(true);
            return "Reaping";
        } else {
            return armor.getNamePrefix();
        }
    }

}
