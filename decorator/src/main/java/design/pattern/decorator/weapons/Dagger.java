package design.pattern.decorator.weapons;

import design.pattern.decorator.PlayStrategy;
import design.pattern.decorator.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class Dagger extends Weapon {
    private static final Logger log = LoggerFactory.getLogger(Dagger.class);

    public Dagger() {
        super();
        setName("Dagger");
        setMinDamage(2);
        setMaxDamage(6);
        setSellingPrice(3);
    }

    public void swing(Player player, Player enemy, PlayStrategy strategy){
        String strategicAction = strategy == PlayStrategy.DEFENSIVE ? "defensively" : "offensively";
        log.debug(player.getName() + " is swinging dagger " + strategicAction);
    }

}
