package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Aircraft's seat for passengers with next fields:
 *
 *<ul>
 *<li> <b>seatNumber</b> - the seat's number, for example "12B"</li>
 *<li> <b>fare</b> - basic fare per seat, no extra charge</li>
 *<li> <b>isRegistered</b> - is passenger registered or not for this seat</li>
 *<li> <b>isSold</b> - is this seat sold or not</li>
 *<li> <b>category</b> - category of the seat - "Business" or "Economy"</li>
 *<li> <b>aircraft</b> - aircraft of the seat</li>
 *</ul>
 *
 * @author Shchepin Maksim
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "seat_number")
    private String seatNumber;
    @Column
    private Integer fare;
    @Column(name = "is_registered")
    private boolean isRegistered;
    @Column(name = "is_sold")
    private boolean isSold;
    @Column(name = "category_id")
    private Category category; //need to be finalized based on Category realization
    @Column(name = "aircraft_id")
    private Aircraft aircraft; //need to be finalized based on Aircraft realization
}
