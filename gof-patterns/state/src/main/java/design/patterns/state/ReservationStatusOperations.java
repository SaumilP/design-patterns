package design.patterns.state;

/**
 * Operation Interface
 */
public interface ReservationStatusOperations {
    ReservationState accept(Reservation reservation) throws UnsupportedStatusTransitionException;
    ReservationState charge(Reservation reservation) throws UnsupportedStatusTransitionException;
    ReservationState cancel(Reservation reservation) throws UnsupportedStatusTransitionException;
}
