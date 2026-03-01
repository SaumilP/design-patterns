package design.pattern.decorator.item;

import design.pattern.decorator.armors.Armor;
import design.pattern.decorator.armors.Boot;
import design.pattern.decorator.armors.Shirt;
import design.pattern.decorator.weapons.Dagger;
import design.pattern.decorator.weapons.Sword;
import design.pattern.decorator.weapons.Weapon;

import java.util.Random;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class ItemFactory implements IItemFactory{

    @Override public Item randomItem() {
        Random random = new Random(100);
        if(random.nextInt() % 2 == 0){
            return randomWeapon();
        } else{
            return randomArmor();
        }
    }

    private Item randomWeapon() {
        Weapon weapon = null;
        Random random = new Random(100);
        switch (random.nextInt()%4){
            case 0: weapon = new Sword(); break;
            case 1: weapon = new Dagger(); break;
            default: break;
        }
        if( random.nextInt() % 15 == 0 ){
            weapon.setRarity(ItemRarity.RARE);
            weapon.setHasBeenIdentified(false);
        } else if ( random.nextInt() % 5 == 0){
            weapon.setRarity(ItemRarity.MAGIC);
            weapon.setHasBeenIdentified(false);
        }
        return weapon;
    }

    private Item randomArmor(){
        Armor armor = null;
        Random random = new Random(100);
        switch (random.nextInt()%4){
            case 0: armor = new Shirt(); break;
            case 2: armor = new Boot(); break;
            default: break;
        }
        if( random.nextInt() % 15 == 0 ){
            armor.setRarity(ItemRarity.RARE);
            armor.setHasBeenIdentified(false);
        } else if ( random.nextInt() % 5 == 0){
            armor.setRarity(ItemRarity.MAGIC);
            armor.setHasBeenIdentified(false);
        }
        return armor;
    }
}
