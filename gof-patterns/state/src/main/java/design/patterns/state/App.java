package design.patterns.state;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * State Design Pattern Client
 */
public class App {
    public static void main(String[] args){
        Reservation reservation = new Reservation();
        reservation.setDate(Calendar.getInstance());
        reservation.setName("Movie Ticket Reservation");
        reservation.setId(123);
        reservation.setPrice(new BigDecimal(9.00));

        try {
            System.out.println("Reservation Accepted...");
            reservation.accept();
            System.out.println("Reservation charge is about to be charged");
            reservation.charge();
            System.out.println("Reservation is about to be cancelled.");
            reservation.cancel();
        } catch (UnsupportedStatusTransitionException e) {
            e.printStackTrace();
        }
    }
}
