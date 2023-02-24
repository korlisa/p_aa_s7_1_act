package app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "aircrafts")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "board_number")
    private String boardNumber;

    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "production_year")
    private Integer productionYear;

    @Column(name = "flying_range")
    private Integer flyingRange;

    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL)
    private Set<Seat> seats;

    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + id +
                ", boardNumber='" + boardNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", productionYear=" + productionYear +
                ", flyingRange=" + flyingRange +
                ", seats=" + seats +
                '}';
    }
}
