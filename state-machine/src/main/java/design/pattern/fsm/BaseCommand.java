package design.pattern.fsm;

/**
 * Base Command for State Transition
 */
public abstract class BaseCommand<T> implements Command {
    private Command next;
    private T context;

    public BaseCommand(T context) {
        this.context = context;
    }

    @Override
    public Command setNext(Command newCommand) {
        next = newCommand;
        return newCommand;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public void execute() throws Exception {
        invoke(context);
        if( hasNext() ){
            next.execute();
        }
    }

    public abstract void invoke(T context) throws Exception;
}
