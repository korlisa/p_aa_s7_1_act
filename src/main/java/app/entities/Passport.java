package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Class Passport with properties <b>number</b>,
 * <b>expiryDate</b>, <b>nationality</b>
 *
 * @author Tamara Ustyan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="passports")
public class Passport {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private String passportNumber;

    @Column(name = "expiry_date")
    private String expiryDate;

    @Column(name = "nationality")
    private String nationality;

    public Passport(String passportNumber, String expiryDate, String nationality) {
        this.passportNumber = passportNumber;
        this.expiryDate = expiryDate;
        this.nationality = nationality;
    }
}
