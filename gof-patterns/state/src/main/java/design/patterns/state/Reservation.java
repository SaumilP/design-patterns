package design.patterns.state;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Reservation Class
 */
public class Reservation {
    private int id;
    private String name;
    private Calendar date;
    private BigDecimal price;
    private ReservationState state = ReservationState.NEW;

    public Reservation() {
    }

    public Reservation(int id, String name, Calendar date, BigDecimal price, ReservationState state) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.price = price;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ReservationState getState() {
        return state;
    }

    public void setState(ReservationState state) {
        if( state != null && state != this.state){
            System.out.println("Reservation#" + id + ": changing status from " +
                              this.state + " to " + state );
            this.state = state;
        }
    }

    public void accept() throws UnsupportedStatusTransitionException {
        setState( state.accept(this));
    }

    public void cancel() throws UnsupportedStatusTransitionException {
        setState( state.cancel(this));
    }

    public void charge() throws UnsupportedStatusTransitionException {
        setState( state.cancel(this));
    }

    @Override public String toString() {
        return "Reservation{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", date=" + date +
               ", price=" + price +
               ", state=" + state +
               '}';
    }
}
