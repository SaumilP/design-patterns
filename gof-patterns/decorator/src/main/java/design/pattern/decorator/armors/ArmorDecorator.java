package design.pattern.decorator.armors;

import design.pattern.decorator.Enemy;
import design.pattern.decorator.Player;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class ArmorDecorator {
    protected Armor armor;

    public ArmorDecorator initWithArmor(){
        if ( armor == null){
            armor = new Armor();
        }
        return this;
    }

    public void didKillPlayer( Player player){

    }

    public void didHitPlayer(Player player, int damage){

    }
}
