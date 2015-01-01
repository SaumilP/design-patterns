package design.pattern.decorator.armors;

import java.util.Random;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class DefenseArmorDecorator extends ArmorDecorator {
    private int extraDefense;

    public ArmorDecorator initWitArmor(){
        Random random = new Random(15);
        extraDefense = random.nextInt();
        return this;
    }

    public int getSellingPrice(){
        return extraDefense*2 + armor.getSellingPrice();
    }

    public String getNameSuffix(){
        if( !armor.isPrefixBeignUsed()){
            armor.setPrefixBeignUsed(true);
            return "of Stone";
        } else {
            return armor.getNameSuffix();
        }
    }

    public String getNamePrefix(){
        if(!armor.isSuffixBeingUsed()){
            armor.setSuffixBeingUsed(true);
            return "Solid";
        } else {
            return armor.getNamePrefix();
        }
    }

}
