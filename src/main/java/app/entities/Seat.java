package app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number")
    private String seatNumber;
    @Column(name = "fare")
    private Integer fare;
    @Column(name = "is_registered")
    private boolean isRegistered;
    @Column(name = "is_sold")
    private boolean isSold;

    @ManyToOne
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", seatNumber='" + seatNumber + '\'' +
                ", fare=" + fare +
                ", isRegistered=" + isRegistered +
                ", isSold=" + isSold +
                ", aircraft=" + aircraft +
                '}';
    }
}
