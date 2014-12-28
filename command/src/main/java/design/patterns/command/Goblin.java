package design.patterns.command;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class Goblin extends Target {
    public Goblin() {
        this.setSize( Size.NORMAL );
        this.setVisibility( Visibility.VISIBLE );
    }

    @Override public String toString(){
        return "Goblin";
    }
}
