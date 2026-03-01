package design.patterns.state.operations;

import design.patterns.state.Reservation;
import design.patterns.state.ReservationState;
import design.patterns.state.ReservationStatusOperations;
import design.patterns.state.UnsupportedStatusTransitionException;

/**
 * Paid Operation
 */
public class PaidReservationOperation implements ReservationStatusOperations {

    @Override public ReservationState accept(Reservation reservation) throws UnsupportedStatusTransitionException {
        throw new UnsupportedStatusTransitionException("accept", ReservationState.ACCEPTED);
    }

    @Override public ReservationState charge(Reservation reservation) throws UnsupportedStatusTransitionException {
        throw new UnsupportedStatusTransitionException("charge", ReservationState.PAID);
    }

    @Override public ReservationState cancel(Reservation reservation) {
        System.out.println("Reservation has been cancelled");
        return ReservationState.CANCELLED;
    }
}
