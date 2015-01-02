package design.patterns.factory;

/**
 * Derived Puzzle class
 */
public class Puzzle extends Obstacle {

    @Override public String action() {
        return "Puzzle";
    }
}
