package app.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "baggage")
public class Baggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "baggage_pieces")
    private Long pieces;

    @Column(name = "baggage_weight")
    private Double weight;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private Booking booking;

    public Baggage(Long pieces, Double weight, Booking booking) {
        this.pieces = pieces;
        this.weight = weight;
        this.booking = booking;
    }

    //    @Enumerated(EnumType.STRING)
//    @Column(name = "baggage_type")
//    private BaggageType type;
}
