package app.services;

import app.entities.Destination;
import app.repositories.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

private final DestinationRepository destinationRepository;

    public DestinationServiceImpl(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }


    @Override
    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    @Override
    public Destination saveDestination(Destination destination) {
        destinationRepository.save(destination);
        return destination;
    }

    @Override
    public void updateDestination(Destination destination) {
        destinationRepository.getDestinationById(destination.getId());
        destinationRepository.save(destination);

    }

    @Override
    public void deleteAllDestination() {
        destinationRepository.deleteAll();

    }

    @Override
    public Destination getDestinationById(Long id) {
        return destinationRepository.getDestinationById(id);
    }

    @Override
    public Destination getDestinationByCity(String city) {
        return destinationRepository.getDestinationByCity(city);
    }

    @Override
    public Destination getDestinationByCountry_name(String country_name) {
        return destinationRepository.getDestinationByCountry_name(country_name);
    }
}
