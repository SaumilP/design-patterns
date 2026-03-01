package design.pattern.fsm;

import design.pattern.fsm.model.Robot;

/**
 * Finite State Machine Client
 */
public class App {
    public static void main(String[] args ){
        CommandFactory factory = new CommandFactory();
        Robot robo = new Robot("Baymax", "Hiro", "123123" ,"2.0");
        System.out.println( String.format("Context[%s] created succesfully", robo.getName()));

        Command firstCommand = factory.create( robo );
        try {
            firstCommand.execute();
        } catch (Exception e) {
            System.out.println("Failed to complete state transitions... " + e.getLocalizedMessage());
        }
    }
}
