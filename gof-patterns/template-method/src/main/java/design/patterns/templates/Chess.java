package design.patterns.templates;

/**
 * Concrete Class implementing primitive operations to carry
 * out steps for Chess Game
 */
public class Chess extends Game {

    @Override public void actionStepOne() {
        System.out.println("Start Chess game");
    }

    @Override public void actionStepTwo() {
        System.out.println("Play Chess");
    }

    @Override public void actionStepThree() {
        System.out.println("Declare winner or tie");
    }
}
