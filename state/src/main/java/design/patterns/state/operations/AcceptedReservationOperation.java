package design.patterns.state.operations;

import design.patterns.state.Reservation;
import design.patterns.state.ReservationState;
import design.patterns.state.ReservationStatusOperations;
import design.patterns.state.UnsupportedStatusTransitionException;

/**
 * Reservation Acceptance related functionality
 */
public class AcceptedReservationOperation implements ReservationStatusOperations {

    @Override public ReservationState accept(Reservation reservation) throws UnsupportedStatusTransitionException {
        throw new UnsupportedStatusTransitionException("accept", ReservationState.ACCEPTED);
    }

    @Override public ReservationState charge(Reservation reservation) {
        System.out.println("Reservation has been accepted, and being charged");
        return ReservationState.PAID;
    }

    @Override public ReservationState cancel(Reservation reservation) {
        System.out.println("Reservation has been cancelled");
        return ReservationState.CANCELLED;
    }
}
