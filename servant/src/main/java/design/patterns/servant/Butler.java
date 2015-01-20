package design.patterns.servant;

/**
 * Butler Information Class
 */
public class Butler {
    private String name;

    public Butler(String name) {
        this.name = name;
    }

    public void prepareJuice(RichRoyalty service){
        System.out.println( name + " -> Ask maid to prepare Drink!!!");
        service.getJuice();
    }

    public void feed(RichRoyalty service){
        System.out.println( name + " -> Prepare the Food for Master Bruce!!!");
        service.readyFood();
    }

    public void readyTheBatMobileForHunt(RichRoyalty service){
        System.out.println( name + " -> Ready Bat Mobile for Batman!!!");
        service.needBatMobile();
    }

    public void supervise(RichRoyalty service){
        System.out.println( name + " -> Time to check on other maid work!!!");
        service.checkOnMaids();
    }
}
