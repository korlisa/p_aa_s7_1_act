package app.services;

import app.entities.Destination;
import app.repositories.DestinationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public Destination getDestinationById(Long id) {
        return destinationRepository.getById(id);
    }
}