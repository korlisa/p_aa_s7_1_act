package app.entities;


import com.neovisionaries.i18n.CountryCode;
import io.swagger.annotations.ApiModel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Entity class for Destination
 * @author Andrey Mitukov
 */

@ApiModel
@NoArgsConstructor
@Setter
@Entity
@ToString
@Table(name = "destinations")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    String city;
    @Transient
    CountryCode country_code;
    @Column
    String country_name;
    @Column
    String continent;

    @Transient
    ZonedDateTime zonedDateTime;

    @Enumerated(EnumType.STRING)
    @Transient
    private List<AirportName> airport_name;

    @Enumerated(EnumType.STRING)
    @Transient
    private List<AirportCode> airport_code;

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry_name() {
        return country_name;
    }

    public String getContinent() {
        return continent;
    }

    /** Method for get country code by country*/
    @PostConstruct
    public CountryCode getCountry_code() {
        return CountryCode.valueOf(CountryCode.findByName(getCountry_name()).get(0).name());
    }

    /** Method for get country date and time by Timezone*/
    @PostConstruct
    public ZonedDateTime getZonedDateTime() {
        Instant nowUtc = Instant.now();
        ZoneId zoneid = ZoneId.of(getContinent().replace(' ', '_') + "/" + getCity().replace(' ', '_'));
        return ZonedDateTime.ofInstant(nowUtc, zoneid).truncatedTo(ChronoUnit.MINUTES);
    }

    /** Method for get Airport by value in enum*/
    @PostConstruct
    public List<AirportName> getAirport_name() {
        return Arrays.stream(AirportName.values())
                .filter(air -> air.countryAndCity.equals(getCountry_name() + "/" + getCity()))
                .collect(Collectors.toList());
    }

    /** Method for get Airport code by value in enum*/
    @PostConstruct
    public List<AirportCode> getAirport_code() {
        return Arrays.stream(AirportCode.values())
                .filter(air -> air.countryAndCity.equals(getCountry_name() + "/" + getCity()))
                .collect(Collectors.toList());
    }

    public Destination(String city, String country_name, String continent) {
        this.city = city;
        this.country_name = country_name;
        this.continent = continent;
    }

    public Destination(Long id, String city, String country_name, String continent) {
        this.id = id;
        this.city = city;
        this.country_name = country_name;
        this.continent = continent;
    }
}
