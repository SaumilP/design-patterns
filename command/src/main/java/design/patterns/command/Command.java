package design.patterns.command;

/**
 * Created by PATEL1 on 12/28/14.
 */
public abstract class Command {
    public abstract void execute(Target target);
    public abstract void undo();

    @Override public abstract String toString();
}
