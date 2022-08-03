package app.services;

import app.entities.Destination;
import app.repositories.DestinationReposirory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DestinationServiceImpl implements DestinationService {

    private final DestinationReposirory destinationReposirory;

    @Autowired
    public DestinationServiceImpl(DestinationReposirory destinationReposirory) {
        this.destinationReposirory = destinationReposirory;
    }
    @Override
    public List<Destination> getAllDestinations() {
        return destinationReposirory.findAll();
    }
    @Override
    public Destination saveDestination(Destination destination) {
        destinationReposirory.save(destination);
        return destination;
    }
    @Override
    public void updateDestination(Destination destination)  {
        destinationReposirory.getById(destination.getId());
        destinationReposirory.save(destination);
    }

    @Override
    public void deleteAllDestination() {
        destinationReposirory.deleteAllInBatch();
    }

    @Override
    public Optional<Destination> getDestinationById(Long id) {
        return destinationReposirory.findById(id);
    }

    @Override
    public Optional<Destination> getDestinationByCountryName(String country_name) {
        return destinationReposirory.findDestinationByCountryName(country_name);
    }

    @Override
    public Optional<Destination> getDestinationByCity(String city) {
        return destinationReposirory.findDestinationByCity(city);
    }


}
