package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private Destination from;
    @ManyToOne
    @JoinColumn(name = "to_id")
    private Destination to;

    private String departureDateTime;

    private String arrivalDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Aircraft aircraft;

    public Flight(Destination from, Destination to, String departureDateTime, String arrivalDateTime, Aircraft aircraft) {
        this.from = from;
        this.to = to;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.aircraft = aircraft;
    }
}


