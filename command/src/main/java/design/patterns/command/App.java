package design.patterns.command;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class App {
    public static void main(String[] args){
        Wizard wizard = new Wizard();
        Goblin goblin = new Goblin();

        goblin.printStatus();

        wizard.castSpell( new ShrinkSpell(), goblin );
        goblin.printStatus();

        wizard.castSpell( new InvisibleSpell(), goblin );
        goblin.printStatus();
        wizard.undoLastSpell();
        goblin.printStatus();
    }
}
