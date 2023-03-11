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
    public void save(Destination destination) {
        destinationRepository.save(destination);
    }

    @Override
    public void update(Destination destination) {
        destinationRepository.save(destination);
    }

    @Override
    public void delete(Long id) {
        destinationRepository.deleteById(id);
    }

    @Override
    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }

    @Override
    public Destination findById(Long id) {
        return destinationRepository.getById(id);
    }
}