package app.repositories;

import app.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Flight Repository - Database Communication
 *
 * @author - Alexander Plekhov
 */

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight findFlightById(long id);

    @Query(value = "SELECT f FROM Flight f " +
            "INNER JOIN f.from depart ON depart.city = ?1 " +
            "INNER JOIN f.to arrive ON arrive.city = ?2")
    List<Flight> findAllFlightByFromAndTo(String from, String to);

}
