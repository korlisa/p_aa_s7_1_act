package app.entities;

import com.neovisionaries.i18n.CountryCode;


import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity class for Destination
 * @author Olga Maslova
 */

@Entity
@Table(name = "Destination")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String city;

    @Column
    private String country_name;

    @Column
    private String continent;

    @Transient
    CountryCode country_code;  // Подключена зависимость nv-i18n (энам с CountryCode)

    @Enumerated(EnumType.STRING)
    @Transient
    private List<AirportName> airport_name;

    @Enumerated(EnumType.STRING)
    @Transient
    private List<AirportCode> airport_code;



    public Destination(Long id, String city, String country_name, String continent) {
        this.id = id;
        this.city = city;
        this.country_name = country_name;
        this.continent = continent;
    }

    public Destination(String city, String country_name, String continent) {
        this.city = city;
        this.country_name = country_name;
        this.continent = continent;
    }

    public Destination() {
    }


    @Override
    public String toString() {
        return "Destination{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", country_name='" + country_name + '\'' +
                ", continent='" + continent + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public CountryCode getCountry_code() {
        return CountryCode.valueOf(CountryCode.findByName(getCountry_name()).get(0).name());
    }


    public List<AirportName> getAirport_name() {
        return Arrays.stream(AirportName.values()).filter(airportName -> airportName.countryAndCity.equals(getCountry_name()
                 + "/" + getCity())).collect(Collectors.toList());
    }



    public List<AirportCode> getAirport_code() {
        return Arrays.stream(AirportCode.values()).filter(airportCode -> airportCode.countryAndCity.equals(getCountry_name()
           + "/" + getCity())).collect(Collectors.toList());
    }

    public void setCountry_code(CountryCode country_code) {
        this.country_code = country_code;
    }

    public void setAirport_name(List<AirportName> airport_name) {
        this.airport_name = airport_name;
    }

    public void setAirport_code(List<AirportCode> airport_code) {
        this.airport_code = airport_code;
    }
}
