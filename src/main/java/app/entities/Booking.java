package app.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "booking")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String bookingNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "booking")
    private List<Passenger> passengers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_person_id", referencedColumnName = "id")
    private Passenger contactPerson;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_settings")
    private NotificationSetting notificationSetting;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_flight_id", referencedColumnName = "id")
    private Flight departureFlight;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "return_flight_id", referencedColumnName = "id")
    private Flight returnFlight;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payer_id", referencedColumnName = "id")
    private Passenger payer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Baggage> baggage;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<AdditionalServices> additionalServices;

}

















