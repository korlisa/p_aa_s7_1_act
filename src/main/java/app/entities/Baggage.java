package app.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "booking")
public class Baggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "baggage_pieces")
    private Long pieces;

    @Column(name = "baggage_weight")
    private Double weight;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "baggage_type")
//    private BaggageType type;
}
