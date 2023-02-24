package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Class Ticket with properties <b>passenger</b>,
 * <b>flight</b>, <b>seat</b>, <b>subcategory</b>,
 * <b>bookingNumber</b>
 *
 * @author Tamara Ustyan
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn (name="passanger_id")
    private Passenger passenger;



//    @OneToOne (cascade=CascadeType.ALL)
//    @JoinColumn (name="seat_id")
//    private Seat seat;
//
//
//    @ManyToOne (cascade=CascadeType.ALL)
//    @JoinColumn (name="seat_id")
//    private Flight flight;



    private Subcategory subcategory;

    @NotNull
    private String bookingNumber;



    public enum Subcategory {
        ECONOMY_CLASS("Эконом"),
        COMFORT_CLASS("Комфорт"),
        BUSINESS_CLASS("Бизнес")
        ;

        private String translation;

        Subcategory(String translation) {this.translation = translation;
        }
    }
}
