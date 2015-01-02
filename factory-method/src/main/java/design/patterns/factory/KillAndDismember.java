package design.patterns.factory;

/**
 * Concrete Class extending AbstractFactory
 */
public class KillAndDismember extends GameElementFactory {

    @Override public Character makeCharacter() {
        return new KungFuGuy();
    }

    @Override public Obstacle makeObstacle() {
        return new NastyWeapon();
    }
}
