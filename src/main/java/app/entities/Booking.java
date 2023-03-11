package app.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingNumber;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "booking")
//    private List<Passenger> passengers;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "contact_person_id", referencedColumnName = "id")
//    private Passenger contactPerson;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_flight_id", referencedColumnName = "id")
    private Flight departureFlight;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "return_flight_id", referencedColumnName = "id")
    private Flight returnFlight;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    private Passenger passenger;

//    @Enumerated(EnumType.STRING)
//    private BookingStatus status;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Baggage> baggage;

    public Booking(Long bookingNumber, Passenger passenger, Flight departureFlight, Flight returnFlight) {
        this.bookingNumber = bookingNumber;
        this.passenger = passenger;
        this.departureFlight = departureFlight;
        this.returnFlight = returnFlight;

    }

    //    @ElementCollection(fetch = FetchType.LAZY)
//    @Enumerated(EnumType.STRING)
//    private List<AdditionalService> additionalServices;

}
