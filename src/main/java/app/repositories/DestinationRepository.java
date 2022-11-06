package app.repositories;

import app.entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

@Query("Select d from Destination d where d.id = :id")
Destination getDestinationById (Long id);


@Query("select d from Destination d where d.city = :city")
Destination getDestinationByCity (String city);


@Query("select d from Destination d where d.country_name = :country_name")
Destination getDestinationByCountry_name (String country_name);











}
