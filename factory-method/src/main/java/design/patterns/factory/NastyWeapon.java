package design.patterns.factory;

/**
 * Specialised Obstacle class
 */
public class NastyWeapon extends Obstacle {

    @Override public String action() {
        return "NastyWeapon";
    }
}
