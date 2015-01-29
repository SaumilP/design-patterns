package design.pattern.fsm;

/**
 * Command interface used to navigate between State Transitioning process
 */
public interface Command {

    /**
     * Method executes command
     */
    public void execute() throws Exception;

    /**
     * Method returns setNext command to be executed for State
     * transition processing.
     *
     * @return Next command for processing
     */
    public Command setNext(Command nextCommand);

    /** method checks and returns boolean value if current command has
     * setNext command chained to it.
     *
     * @return <b>True</b> if setNext command has been chained, otherwise <b>False</b>
     */
    public boolean hasNext();
}
