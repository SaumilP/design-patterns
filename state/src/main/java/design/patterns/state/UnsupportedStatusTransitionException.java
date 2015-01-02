package design.patterns.state;

/**
 * Custom Exception
 */
public class UnsupportedStatusTransitionException extends Exception {
    private String stateName;
    private ReservationState state;

    public UnsupportedStatusTransitionException(String stateName,
                                                ReservationState state){
        this.state = state;
        this.stateName = stateName;
    }
}
