package design.pattern.decorator.armors;

import design.pattern.decorator.Enemy;
import design.pattern.decorator.item.Item;
import design.pattern.decorator.Player;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class Armor extends Item {
    private ArmorType type;
    private int defense;
    private int percentDamageReflected;
    private int percentDamageAbsorbed;

    // ----- OPERATIONS
    public String statDescription(){
        if(this.isHasBeenIdentified()){
            return "Defense: " + defense;
        } else {
            return "Defense: ?";
        }
    }

    public void didKillPlayer(Player player){

    }

    public void didHitPlayer(Player player, int damage){

    }

    public Armor() {
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getPercentDamageReflected() {
        return percentDamageReflected;
    }

    public void setPercentDamageReflected(int percentDamageReflected) {
        this.percentDamageReflected = percentDamageReflected;
    }

    public int getPercentDamageAbsorbed() {
        return percentDamageAbsorbed;
    }

    public void setPercentDamageAbsorbed(int percentDamageAbsorbed) {
        this.percentDamageAbsorbed = percentDamageAbsorbed;
    }

    public ArmorType getType() {
        return type;
    }

    public void setType(ArmorType type) {
        this.type = type;
    }
}
