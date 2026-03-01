package design.patterns.factory;

/**
 * Concrete Class extending AbstractFactory
 */
public class KittiesAndPuzzles extends GameElementFactory {

    @Override public Character makeCharacter() {
        return new Kitty();
    }

    @Override public Obstacle makeObstacle() {
        return new Puzzle();
    }
}
