package app.entities;

import app.util.AircraftInterior;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Aircraft with next fields:
 *<ul>
 *<li> <b>boardNumber</b> - the board's number, for example "RA-12981"</li>
 *<li> <b>brand</b> - aircraft's manufactured company, for example "Boeing"</li>
 *<li> <b>model</b> - aircraft's model, for example "767-300ER"</li>
 *<li> <b>productionYear</b> - aircraft production year, for example "2007"</li>
 *<li> <b>flyingRange</b> - maximal aircraft's flying distance, for example "11 305" km</li>
 *<li> <b>seats</b> - the set of seats for passengers</li>
 *<li> <b>AircraftInterior</b> - auxiliary interface for seat's creation</li>
 *</ul>
 *
 * @author Shchepin Maksim
 */

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "aircrafts")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "board_number")
    private String boardNumber;
    @Column
    private String brand;
    @Column
    private String model;
    @Column(name = "production_year")
    private Integer productionYear;
    @Column(name = "flying_range")
    private Integer flyingRange;
    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL)
    private Set<Seat> seats;

    @Transient
    private AircraftInterior interior;// in progress interior and interior builder(seems like another task)

    public Aircraft(Long id, String boardNumber, String brand, String model, Integer productionYear, Integer flyingRange, Set<Seat> seats) {
        this.id = id;
        this.boardNumber = boardNumber;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.flyingRange = flyingRange;
        this.seats = seats;
    }

    @Transient
    public Set<Seat> getSeatsByCategory(Category category) {
        return seats.stream()
                .filter(seat -> seat.getCategory() == category)
                .collect(Collectors.toSet());
    }
}