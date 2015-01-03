package design.patterns.memento;

/**
 * Memento Design Pattern Client
 */
public class App {
    public static void main(String[] args){
        Gamer gamer = new Gamer(5);
        Memento memento = gamer.createMemento();
        for(int i = 0; i < 5; i++){
            System.out.println("==== " + i);
            System.out.println("Current : " + gamer);
            gamer.bet();
            System.out.println("Money : " + gamer.getMoney());

            if  (gamer.getMoney() > memento.getMoney() ){
                System.out.println("Save current state");
                memento = gamer.createMemento();
            } else if ( gamer.getMoney() < memento.getMoney()/2){
                System.out.println("Restore previous state");
                gamer.restoreMemento(memento);
            }

            try{
                Thread.sleep(1000);
            } catch (InterruptedException ex){

            }
            System.out.println("");
        }
    }
}
