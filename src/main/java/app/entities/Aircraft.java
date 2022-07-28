package app.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Class Aircraft with properties <b>name</b> (side number of aircraft),
 * <b>brand</b>, <b>model</b>, <b>yearOfProduction</b>,
 * <b>flightDistance</b>, <b>speed</b>
 *
 * @author Eugene Kolyshev
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "t_aircraft")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Field <b>name</b> with unique Aircraft`s side number
     */
    @Column(name = "sideNumber")
    @NonNull
    private String name;

    @Column
    @NonNull
    private String brand;

    @Column
    @NonNull
    private String model;

    @Column
    @NonNull
    private Integer yearOfProduction;

    @Column
    @NonNull
    private Integer flightDistance;

    @Column
    @NonNull
    private Integer speed;

    /** Field with Set of aircraft seats
     * @see Seat
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "aircraft_id")
    private Set<Seat> seats;

}