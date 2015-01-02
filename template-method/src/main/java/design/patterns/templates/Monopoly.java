package design.patterns.templates;

/**
 * Concrete Class implementing primitive operations to carry
 * out steps for Chess Game
 */
public class Monopoly extends Game {

    @Override public void actionStepOne() {
        System.out.println("Start Monopoly game");
    }

    @Override public void actionStepTwo() {
        System.out.println("Play Monopoly");
    }

    @Override public void actionStepThree() {
        System.out.println("Declare Monopoly winner");
    }
}
