package design.patterns.templates;

/**
 * Abstract class denoting required operations for concrete classes
 */
public abstract class Game {

    protected abstract void actionStepOne();
    protected abstract void actionStepTwo();
    protected abstract void actionStepThree();

    public final void play(){
        actionStepOne();
        actionStepTwo();
        actionStepThree();
    }
}
