package app.services;

import app.entities.Destination;

import java.util.List;
import java.util.Optional;

public interface DestinationService {
    List<Destination> getAllDestinations();

    Destination saveDestination(Destination destination);

    void updateDestination(Destination destination);

    void deleteAllDestination();

    Optional<Destination> getDestinationById(Long id);

    Optional<Destination> getDestinationByCountryName(String country_name);

    Optional<Destination> getDestinationByCity(String city);
}
