package app.services;

import app.repositories.DestinationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;
}