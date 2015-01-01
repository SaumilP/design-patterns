package design.pattern.decorator.weapons;

import design.pattern.decorator.PlayStrategy;
import design.pattern.decorator.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class Sword extends Weapon {
    private static final Logger log = LoggerFactory.getLogger(Sword.class);

    public Sword(){
        super();
        setName("Sword");
        setMinDamage(5);
        setMaxDamage(8);
        setSellingPrice(4);
    }
    public void swing(Player player, Player enemy, PlayStrategy strategy){
        String strategicAction = strategy == PlayStrategy.DEFENSIVE ? "defensively" : "offensively";
        log.debug(player.getName() + " is swinging sword " + strategicAction);
    }
}
