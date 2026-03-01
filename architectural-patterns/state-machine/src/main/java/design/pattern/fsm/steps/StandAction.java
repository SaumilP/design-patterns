package design.pattern.fsm.steps;

import design.pattern.fsm.BaseCommand;
import design.pattern.fsm.model.Robot;

/**
 * StandAction for Robot
 */
public class StandAction extends BaseCommand {

    public StandAction(Object context) {
        super(context);
    }

    @Override
    public void invoke(Object context) throws Exception {
        if (context.getClass() != Robot.class ) {
            return;
        }
        Robot robot = (Robot)context;
        System.out.println( String.format("Request has been received by %s to stand up.." ,robot.getName()) );
    }
}
