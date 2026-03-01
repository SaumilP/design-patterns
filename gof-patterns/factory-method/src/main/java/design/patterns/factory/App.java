package design.patterns.factory;

/**
 * Abstract Factory Pattern Client
 */
public class App {
    public static void main(String[] args){
        GameEnvironment firstEnv = new GameEnvironment( new KittiesAndPuzzles() );
        GameEnvironment secondEnv = new GameEnvironment(new KillAndDismember() );

        firstEnv.play();
        secondEnv.play();
    }
}
