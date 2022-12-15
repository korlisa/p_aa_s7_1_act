package app.entities;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Flight flight;
}
