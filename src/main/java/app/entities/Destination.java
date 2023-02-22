package app.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "destinations")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String countryName;

    private String continent;

    private Long countryCode;

    @OneToMany(mappedBy = "from")
    private List<Flight> racesFrom;

    @OneToMany(mappedBy = "to")
    private List<Flight> racesTo;


}

