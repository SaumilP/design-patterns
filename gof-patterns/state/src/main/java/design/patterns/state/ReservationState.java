package design.patterns.state;

import design.patterns.state.operations.AcceptedReservationOperation;
import design.patterns.state.operations.CancelReservationOperation;
import design.patterns.state.operations.NewReservationOperation;
import design.patterns.state.operations.PaidReservationOperation;

/**
 * Reservation State Enumerations
 */
public enum ReservationState implements ReservationStatusOperations {
    NEW(new NewReservationOperation()),
    ACCEPTED(new AcceptedReservationOperation()),
    PAID(new PaidReservationOperation()),
    CANCELLED(new CancelReservationOperation());

    private final ReservationStatusOperations operations;

    ReservationState(ReservationStatusOperations operations){
        this.operations = operations;
    }

    @Override public ReservationState accept(Reservation reservation) throws UnsupportedStatusTransitionException {
        return operations.accept(reservation);
    }

    @Override public ReservationState charge(Reservation reservation) throws UnsupportedStatusTransitionException {
        return operations.charge(reservation);
    }

    @Override public ReservationState cancel(Reservation reservation) throws UnsupportedStatusTransitionException {
        return operations.cancel(reservation);
    }
}
