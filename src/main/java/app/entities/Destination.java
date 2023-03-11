package app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "destinations")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String countryName;

    private String continent;

    private Long countryCode;

    @JsonIgnore
    @OneToMany(mappedBy = "from")
    private List<Flight> racesFrom;

    @JsonIgnore
    @OneToMany(mappedBy = "to")
    private List<Flight> racesTo;


    public Destination(String city, String countryName, String continent, Long countryCode) {
        this.city = city;
        this.countryName = countryName;
        this.continent = continent;
        this.countryCode = countryCode;
    }
}

