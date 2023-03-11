package app.entities;

import lombok.*;

import javax.persistence.*;


/**
 * Class Passenger with properties <b>email</b>,
 * <b>firstName</b>, <b>lastName</b>, <b>middleName</b>,
 * <b>phoneNumber</b>, <b>dateOfBirth</b>
 * <p>
 * создал поля пассажира, а также поле номера билета
 *
 * @author Tamara Ustyan
 */
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passenger")
public class Passenger {
//    @NonNull
//    @Column(name = "firstName")
//    private String firstName;
//    @NonNull
//    @Column(name = "lastName")
//    private String lastName;
//    @NonNull
//    @Column(name = "middleName")
//    private String middleName;
//    @NonNull
//    @Column(name = "date_of_birth")
//    private int dateOfBirth;
//    @NonNull
//    @Column(name = "gender_of_the_Passenger")
//    private int genderOfThePassenger;

//    @OneToOne(mappedBy = "passenger")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @JsonBackReference
//    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @NonNull
    @Column(name = "email")
    private String email;


    @OneToOne(mappedBy = "passenger", cascade = CascadeType.ALL)
//    @JsonBackReference
    private Passport passport;

    public Passenger(@NonNull String phoneNumber, @NonNull String email, Passport passport) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passport = passport;
    }
}


