package design.patterns.flyweight;

/**
 * Client for Flyweight Design Pattern
 */
public class App {
    private static NoodleFlavor[] flavors = new NoodleFlavor[100];
    private static NoodleOrderContext[] tables = new NoodleOrderContext[100];
    private static int ordersPrepared = 0;
    private static NoodleFactory factory;

    public static void main(String[] args){
        factory = new NoodleFactory();
        takeNoodlesOrder("Daoww-sshyaoww men",2);
        takeNoodlesOrder("Daoww-sshyaoww men",2);
        takeNoodlesOrder("Da-dan myen",434);
        takeNoodlesOrder("Da-dan myen",434);
        takeNoodlesOrder("Yoh por myen",1);
        takeNoodlesOrder("Jaa-jyang myen",45);
        takeNoodlesOrder("Jaa-jyang myen",45);
        takeNoodlesOrder("Lyang-pee",32);

        for(int i =0 ; i < ordersPrepared; i++ ){
            flavors[i].serveNoodles( tables[i] );
        }

        System.out.println("\nTotal Noodle dishes prepared : " + factory.getNoOfGlassNoodleBowlsPrepared() );
    }

    private static void takeNoodlesOrder(String flavor, int tableNumber) {
        flavors[ordersPrepared] = factory.getFlavor(flavor);
        tables[ordersPrepared++] = new NoodleOrderContext(tableNumber);
    }
}
