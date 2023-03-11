package app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passenger_passports")
public class Passport {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "number")
    private String passportNumber; // Номер паспорта

    @NonNull
    @Column(name = "lastName")
    private String lastName; // Фамилия

    @NonNull
    @Column(name = "firstName")
    private String firstName; // Имя


    @Column(name = "middle_Name")
    private String middleName; // Отчество

    @NonNull
    @Column(name = "date_of_birth")
    private String dateOfBirth; // Дата рождения

    @NonNull
    @Column(name = "expiry_Date")
    private String expiryDate; // Срок действия паспорта

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id")
    @JsonManagedReference
    private Passenger passenger;

    public Passport(
            @NonNull String passportNumber,
            @NonNull String lastName,
            @NonNull String firstName,
            String middleName,
            @NonNull String dateOfBirth,
            @NonNull String expiryDate,
            Passenger passenger
    ) {
        this.passportNumber = passportNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.expiryDate = expiryDate;
        this.passenger = passenger;
    }
}
