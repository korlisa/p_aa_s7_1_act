package app.entities;

import app.util.Category;
import lombok.*;
import javax.persistence.*;


/**
 * Class Seat with properties.
 *
 * <b>seatNumber</b> - number of passengers seat, for example "12B"
 * <b>fare</b> - basic fare per seat, no extra charge
 * <b>isRegistered</b> - is passenger registered or not for this seat
 * <b>isSold</b> - is this seat sold or not
 * <b>category</b> - category of this seat - "Business" oe "Economy"
 * @see Category
 *
 * @author Eugene Kolyshev
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "t_seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "seatNumber")
    @NonNull
    private String seatNumber;

    @Column(name = "fare")
    @NonNull
    private Integer fare = 0;

    @Column(name = "isRegistered")
    @NonNull
    private Boolean isRegistered = false;

    @Column(name = "isSold")
    @NonNull
    private Boolean isSold = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    @NonNull
    private Category category;

}
