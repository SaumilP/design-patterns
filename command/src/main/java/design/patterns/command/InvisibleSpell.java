package design.patterns.command;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class InvisibleSpell extends Command {
    private Target target;

    public InvisibleSpell() {
        this.target = null;
    }

    @Override
    public void execute(Target target) {
        target.setVisibility( Visibility.INVISIBLE );
    }

    @Override
    public void undo() {
        if ( target != null ){
            target.setVisibility( Visibility.VISIBLE );
        }
    }

    @Override
    public String toString() {
        return "Invisibility spell";
    }
}
