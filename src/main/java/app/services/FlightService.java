package app.services;

import app.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
}
