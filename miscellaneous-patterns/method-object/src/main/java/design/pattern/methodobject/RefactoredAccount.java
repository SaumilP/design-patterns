package design.pattern.methodobject;

/**
 * Refactored Account Class
 */
public class RefactoredAccount {
    private double value;
    private int quantity;
    private int calcDateInYear;
    private int delta = 100;

    public RefactoredAccount(double value, int quantity, int calcDateInYear, int delta) {
        this.value = value;
        this.quantity = quantity;
        this.calcDateInYear = calcDateInYear;
        this.delta = delta;
    }

    public double compute(){
        double tempVal1 = ( value * quantity ) + delta;
        double tempVal2 = ( value * calcDateInYear ) + 100;
        if( ( calcDateInYear - tempVal1 ) > 100 ) {
            tempVal2 -= 20;
        }
        double tempVal3 = tempVal2 * 7;
        return tempVal3;
    }

}
