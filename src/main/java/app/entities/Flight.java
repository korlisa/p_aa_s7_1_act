package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import java.time.LocalDateTime;

/**
 * This class represent an Entity Flight, includes fields:
 * destination from/to;
 * localDateTime departure/arrival;
 * aircraft - assigned aircraft;
 * flight status - ON_TIME, DELAY, CANCELED;
 *
 * @author - Alexander Plekhov
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_from_id")
    @NonNull
    private Destination from;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_to_id")
    @NonNull
    private Destination to;

    @Column
    @NonNull
    private LocalDateTime departureDateTime;
    @Column
    @NonNull
    private LocalDateTime arrivalDateTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aircraft_id")
    @NonNull
    private Aircraft aircraft;

    @Enumerated(EnumType.STRING)
    private FlightStatus flightStatus;

    public enum FlightStatus {
        ON_TIME("По плану"),
        DELAY("Задержан"),
        CANCELED("Отменён");

        private String flightStatus;

        FlightStatus(String flightStatus) {
            this.flightStatus = flightStatus;
        }

        public String toString() {
            return this.flightStatus;
        }
    }

}
