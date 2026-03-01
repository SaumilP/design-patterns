package design.pattern.decorator.weapons;

import design.pattern.decorator.Player;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class LifeLeechDecorator extends Weapon{
    private float lifeLeechPercentage;

    public float getLifeLeechPercentage() {
        return lifeLeechPercentage;
    }

    public void setLifeLeechPercentage(float lifeLeechPercentage) {
        this.lifeLeechPercentage = lifeLeechPercentage;
    }

    @Override public void didHitEnemy(Player player, Player enemy, float damage) {
        super.didHitEnemy(player, enemy, damage);
    }
}
