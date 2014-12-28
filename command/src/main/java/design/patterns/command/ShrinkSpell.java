package design.patterns.command;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class ShrinkSpell extends Command {
    private Size oldSize;
    private Target target;

    @Override
    public void execute(Target target) {
        oldSize = target.getSize();
        target.setSize( Size.SMALL );
        this.target = target;
    }

    @Override
    public void undo() {
        if( oldSize != null && target != null ){
            target.setSize( oldSize );
        }
    }

    @Override
    public String toString() {
        return "Shrink Spell";
    }
}
