package app.repositories;

import app.entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DestinationReposirory extends JpaRepository<Destination, Long> {
    @Query("SELECT p FROM Destination p WHERE  p.country_name = :country_name")
    Optional<Destination> findDestinationByCountryName(String country_name);
    @Query("SELECT p FROM Destination p WHERE p.city = :city")
    Optional<Destination> findDestinationByCity(String city);
}
