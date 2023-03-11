package app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Class Ticket with properties <b>passenger</b>,
 * <b>flight</b>, <b>seat</b>, <b>subcategory</b>,
 * <b>bookingNumber</b>
 *
 * @author Tamara Ustyan
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seat_id")
    private Seat seat;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id")
    private Flight flight;


    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private Long bookingNumber;

    public Ticket(Long bookingNumber, Flight flight, Seat seat, Passenger passenger) {
        this.bookingNumber = bookingNumber;
        this.flight = flight;
        this.seat = seat;
        this.passenger = passenger;
    }
}
