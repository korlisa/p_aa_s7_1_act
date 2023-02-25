package app.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


/**
 * Class Passenger with properties <b>email</b>,
 * <b>firstName</b>, <b>lastName</b>, <b>middleName</b>,
 * <b>phoneNumber</b>, <b>dateOfBirth</b>
 * <p>
 * создал поля пассажира, а также поле номера билета
 *
 * @author Tamara Ustyan
 */

@ToString
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Passenger")
public class Passenger extends User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "firstName")
    private String firstName;
    @NonNull
    @Column(name = "lastName")
    private String lastName;
    @NonNull
    @Column(name = "e_mail")
    private String email;
    @Column(name = "telegram")
    private long telegram;
    @NonNull
    @Column(name = "LocalDate_date_of_birth")
    private int localDateDateOfBirth;
    @NonNull
    @Column(name = "gender_of_the_Passenger")
    private int genderOfThePassenger;

    /////////////////////////////////// - добавленное
    @NonNull
    @Column(name = "middleName")
    private String middleName;
    @NonNull
    @Column(name = "phoneNumber")
    private String phoneNumber;

//    @OneToMany(mappedBy = "passenger")
//    private List<Baggage> baggages;


//    @OneToOne (mappedBy = "passenger")
//    private Passport passport;
//
//    @OneToOne
//    private Ticket ticket;


//    @ManyToOne
//    @JoinColumn(name = "booking_id")
//    private Booking booking;

    /////

//    @OneToOne(mappedBy = "contactPerson") // нужен ли
//    private Booking forContact;

//    @OneToOne(mappedBy = "payer")   // нужен ли
//    private Booking forPayer;

    ////


    /////////////////////////////////////////////////////////////////////

    //Collection<Role> roles,
    public Passenger(Long id, @Email @NotEmpty String email, long telegram, @NotEmpty String password,
                     @NonNull String firstName, @NonNull String lastName, @NonNull String email1,
                     @NonNull int localDateDateOfBirth, @NonNull int genderOfThePassenger) {
//        super(id, email, password, roles);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email1;
        this.telegram = telegram;
        this.localDateDateOfBirth = localDateDateOfBirth;
        this.genderOfThePassenger = genderOfThePassenger;
    }


}


