package app.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Class Passenger with properties <b>email</b>,
 * <b>firstName</b>, <b>lastName</b>, <b>middleName</b>,
 * <b>phoneNumber</b>, <b>dateOfBirth</b>
 *
 * @author Tamara Ustyan
 */
@Table
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "passengers")
public class Passenger extends User {

        @Column(name = "first_name")
        @NonNull
        private String firstName;

        @Column(name = "last_name")
        @NonNull
        private String lastName;

        @Column(name = "middle_name")
        @NonNull
        private String middleName;

        @Column(name="phone_number")
        @NonNull
        private String phoneNumber;

        @Column(name="date_of_birth")
        @NonNull
        private LocalDate dateOfBirth;

    /** Field with Passport of passenger passport
     * @see Passport
     */
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "passport_id")
        private Passport passport;


        public Passenger(String firstName, String lastName, String phoneNumber, String email, String middleName, LocalDate dateOfBirth, Passport passport) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.middleName = middleName;
            this.dateOfBirth = dateOfBirth;
            this.passport = passport;
        }

}

