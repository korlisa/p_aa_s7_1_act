package app.entities;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
/**
 * Class Ticket with properties <b>passenger</b>,
 * <b>flight</b>, <b>seat</b>, <b>subcategory</b>,
 * <b>bookingNumber</b>
 *
 * @author Tamara Ustyan
 */
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tickets")

public class Ticket {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /** Field with Passenger of tickets passenger
     * @see Passenger
     */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "passenger_id")
    //@NotFound(action = NotFoundAction.IGNORE)
    private Passenger passenger;

    /** Field with Flight of tickets flight
     * @see Flight
     */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "flight_id")
   // @NotFound(action = NotFoundAction.IGNORE)
    private Flight flight;

    /** Field with Seat of tickets seat
     * @see Seat
     */
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private Subcategory subcategory;

    public Ticket(Passenger passenger, Flight flight, Seat seat, Subcategory subcategory) {
        this.passenger = passenger;
        this.flight = flight;
        this.seat = seat;
        this.subcategory = subcategory;
    }

    public enum Subcategory {
        BASIC("базовый"),
        STANDARD("стандарт"),
        PLUS("плюс");

        private String subcategory;

       Subcategory(String subcategory) {
            this.subcategory = subcategory;
        }

    }

    @Column(name = "booking_number")
    private String bookingNumber;


}
