package design.patterns.flyweight;

/**
 * Concrete Flyweight class
 */
public class NoodleFlavor extends NoodleOrder {
    private String noodleFlavor;

    public NoodleFlavor(String noodleFlavor) {
        this.noodleFlavor = noodleFlavor;
    }

    public String getNoodleFlavor() {
        return noodleFlavor;
    }

    @Override public void serveNoodles(NoodleOrderContext context) {
        System.out.println("Serving glass noodle to table number " + context.getTableNumber() );
    }
}
