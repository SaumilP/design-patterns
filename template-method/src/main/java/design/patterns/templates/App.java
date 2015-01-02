package design.patterns.templates;

/**
 * Template Method Design Pattern Client
 */
public class App {
    public static void main(String[] args){
        Game chess = new Chess();
        Game monopoly = new Monopoly();
        chess.play();
        monopoly.play();
    }
}
