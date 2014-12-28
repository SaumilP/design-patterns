package design.patterns.visitor;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class App {
    public static void main(String[] args ){
        Commander commander = new Commander(new Sergeant(new Soldier(),
                new Soldier(), new Soldier()), new Sergeant(new Soldier(),
                new Soldier(), new Soldier()));
        commander.accept( new SoldierVisitor());
        commander.accept( new SergeantVisitor());
        commander.accept( new CommanderVisitor());
    }
}
