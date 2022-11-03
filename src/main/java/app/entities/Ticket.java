package app.entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

/**
 * Class Ticket with properties
 * <b>passenger</b>,
 * <b>flight</b>,
 * <b>seat</b>,
 * <b>subcategory</b>,
 * <b>bookingNumber</b>
 *
 * @author Darya Shonoeva
 */

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Passenger passenger;

    @Column
    private Flight flight;

    @Column
    private Category subcategory;

    @Column
    private Seat seat;

    @Column
    private int bookingNumber;
}
