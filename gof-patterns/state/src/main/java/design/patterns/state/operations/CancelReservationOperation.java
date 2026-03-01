package design.patterns.state.operations;

import design.patterns.state.Reservation;
import design.patterns.state.ReservationState;
import design.patterns.state.ReservationStatusOperations;
import design.patterns.state.UnsupportedStatusTransitionException;

/**
 * Cancellation Operation
 */
public class CancelReservationOperation implements ReservationStatusOperations {

    @Override public ReservationState accept(Reservation reservation) throws UnsupportedStatusTransitionException {
        throw new UnsupportedStatusTransitionException("accept", ReservationState.ACCEPTED);
    }

    @Override public ReservationState charge(Reservation reservation) throws UnsupportedStatusTransitionException {
        throw new UnsupportedStatusTransitionException("charge", ReservationState.PAID);
    }

    @Override public ReservationState cancel(Reservation reservation) throws UnsupportedStatusTransitionException {
        throw new UnsupportedStatusTransitionException("cancel", ReservationState.CANCELLED);
    }
}
