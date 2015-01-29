package design.pattern.fsm;

import design.pattern.fsm.model.Robot;
import design.pattern.fsm.steps.SitAction;
import design.pattern.fsm.steps.StandAction;
import design.pattern.fsm.steps.TurnAction;
import design.pattern.fsm.steps.WalkAction;

/**
 * Command Factory preparing/chaining Commands
 */
public class CommandFactory {

    public Command create(Robot context){
        /** Bunch of Commands*/
        Command sitCmd = new SitAction(context);
        Command walkCmd = new WalkAction(context);
        Command turnCmd = new TurnAction(context);
        Command standCmd = new StandAction(context);

        standCmd.setNext( walkCmd )
                .setNext( turnCmd )
                .setNext( sitCmd );

        System.out.println("Command Chaining completed...");

        return standCmd;
    }
}
