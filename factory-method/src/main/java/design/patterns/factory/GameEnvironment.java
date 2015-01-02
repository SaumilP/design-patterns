package design.patterns.factory;

/**
 * Control class used to setup character & obstacles
 */
public class GameEnvironment {
    private GameElementFactory factory;
    private Character character;
    private Obstacle obstacle;

    public GameEnvironment(GameElementFactory factory) {
        this.factory = factory;
        character = factory.makeCharacter();
        obstacle = factory.makeObstacle();
    }

    public void play(){
        character.interactWith(obstacle);
    }
}
