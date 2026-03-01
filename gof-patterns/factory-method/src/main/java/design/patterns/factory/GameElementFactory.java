package design.patterns.factory;

/**
 * Abstract class for Game Element actions
 */
public abstract class GameElementFactory {
    public abstract Character makeCharacter();
    public abstract Obstacle makeObstacle();
}
