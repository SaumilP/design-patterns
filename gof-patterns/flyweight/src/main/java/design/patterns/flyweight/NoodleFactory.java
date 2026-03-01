package design.patterns.flyweight;

/**
 * Flyweight Class Factory
 */
public class NoodleFactory {
    private NoodleFlavor[] noodles = new NoodleFlavor[10];
    private int noOfGlassNoodleBowlsPrepared = 0;

    public int getNoOfGlassNoodleBowlsPrepared() {
        return noOfGlassNoodleBowlsPrepared;
    }

    public NoodleFlavor getFlavor(String noodleFlavor) {
        if( noOfGlassNoodleBowlsPrepared > 0 ){
            for (int i = 0; i < noOfGlassNoodleBowlsPrepared; i++) {
                if( noodleFlavor.equals( (noodles[i]).getNoodleFlavor() ) ){
                    return noodles[i];
                }
            }
        }
        noodles[noOfGlassNoodleBowlsPrepared] = new NoodleFlavor(noodleFlavor);
        return noodles[noOfGlassNoodleBowlsPrepared++];
    }
}
