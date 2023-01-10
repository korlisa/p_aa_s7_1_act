package app.entities;

import lombok.Data;
import javax.persistence.*;


@Entity
@Data
@Table(name = "baggage")
public class Baggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "baggage_pieces")
    private Integer pieces;

    @Column(name = "baggage_weight")
    private Double weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "baggage_type")
    private BaggageType type;
}
