package design.patterns.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class App {
    private static final Logger Log = LoggerFactory.getLogger(App.class);
    public static void main( String[] args){
        Hero mage = new Hero.HeroBuilder(Profession.MAGE, "Richard")
                .withHairColor(HairColor.BLACK)
                .withWeapon( Weapon.DAGGER )
                .build();
        Log.debug(mage.toString());

        Hero warrior = new Hero.HeroBuilder( Profession.WARRIOR, "Edward")
                .withHairType(HairType.CURLY)
                .withWeapon( Weapon.BOW )
                .build();
        Log.debug(warrior.toString());
    }
}
