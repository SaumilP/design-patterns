package design.pattern.methodobject;

/**
 * Old crappy written class for account related calculation
 */
public class Account {
    private static final int delta = 3;

    public double calculateValue(double value, int quantity, int calcDateInYear ){
        double tempVal1 = ( value * quantity ) + delta;
        double tempVal2 = ( value * calcDateInYear ) + 100;
        if( ( calcDateInYear - tempVal1 ) > 100 ) {
            tempVal2 -= 20;
        }
        double tempVal3 = tempVal2 * 7;
        return tempVal3;
    }
}
