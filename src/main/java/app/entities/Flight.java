package app.entities;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * This class represent an Entity Flight, includes fields:
 * destination from/to;
 * localDateTime departure/arrival;
 * aircraft - assigned aircraft;
 * flight status - ACCORDING_TO_PLAN, DELAY, CANCELED;
 *
 * @author - Babkin Artyom
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="from")
    private Destination from;

    @Column(name="to")
    private Destination to;

    @Column(name="departureDateTime")
    private LocalDateTime departureDateTime;

    @Column(name="arrivalDateTime")
    private LocalDateTime arrivalDateTime;

    @Column(name="aircraft")
    private Aircraft aircraft;

    public enum FlightStatus {
        ACCORDING_TO_PLAN("По плану"),
        DETAINED("Задержан"),
        CANCELLED("Отменен"),
        BOARDING("Посадка"),
        DEPARTED("Улетел"),
        DELAYED("Задерживается");

        private String translation;
        FlightStatus(String translation) {
            this.translation = translation;
        }
        public String getTranslation() {
            return translation;
        }
    }
}
