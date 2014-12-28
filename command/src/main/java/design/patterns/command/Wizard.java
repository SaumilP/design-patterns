package design.patterns.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class Wizard extends Target {

    private static final Logger Log = LoggerFactory.getLogger(Wizard.class);
    private Command lastCommand;

    public Wizard(){
        this.setSize( Size.NORMAL );
        this.setVisibility( Visibility.VISIBLE );
        lastCommand = null;
    }

    public void castSpell(Command command, Target target){
        Log.debug( this + " casts " + command + " at " + target);
        command.execute( target );
        lastCommand = command;
    }

    public void undoLastSpell(){
        if ( lastCommand != null ){
            Log.debug(this + " undoes " + lastCommand);
            lastCommand.undo();
        }
    }

    @Override
    public String toString() {
        return "Wizard";
    }
}
