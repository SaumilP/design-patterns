package design.pattern.decorator.weapons;

import design.pattern.decorator.item.Item;
import design.pattern.decorator.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class Weapon extends Item {
    private static final Logger log = LoggerFactory.getLogger(Weapon.class);

    private int minDamage;
    private int maxDamage;
    private int critChance;
    private int critPercentBonusDamage;

    // --- CONSTRUCTORS
    public Weapon(int minDamage, int maxDamage) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public Weapon() {
    }

    // ---- ACCESSORS

    public int getCritPercentBonusDamage() {
        return critPercentBonusDamage;
    }

    public void setCritPercentBonusDamage(int critPercentBonusDamage) {
        this.critPercentBonusDamage = critPercentBonusDamage;
    }

    public int getCritChance() {
        return critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    public float getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    // ----- OPERATIONS
    public void willHitEnemy(Player player, Player enemy){

    }

    public void didHitEnemy(Player player, Player enemy, float damage){

    }

    public void didDefeatEnemy(Player player, Player enemy){

    }
}
