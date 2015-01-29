package design.pattern.fsm.steps;

/**
 * RunAction - One of the way to extend basic state to hierarchical state
 * to re-use existing state transitioning related code...
 */
public class RunAction extends WalkAction {

    public RunAction(Object context) {
        super(context);
    }

}
