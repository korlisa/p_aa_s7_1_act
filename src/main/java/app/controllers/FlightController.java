package app.controllers;

import app.entities.Flight;
import app.services.AircraftService;
import app.services.DestinationService;
import app.services.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    private final FlightService flightService;

    private final DestinationService destinationService;

    private final AircraftService aircraftService;

    public FlightController(FlightService flightService, DestinationService destinationService, AircraftService aircraftService) {
        this.flightService = flightService;
        this.destinationService = destinationService;
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public List<Flight> getAll() {
        return flightService.findAll();
    }

    @PostMapping
    public ResponseEntity<Flight> create(@RequestBody Flight flight) {
        flight = new Flight(
                destinationService.findById(1L),
                destinationService.findById(2L),
                "03.11.2010",
                "03.12.2010",
                aircraftService.findById(1L)
        );
        flightService.save(flight);
        flight = new Flight(
                destinationService.findById(2L),
                destinationService.findById(1L),
                "05.11.2010",
                "05.12.2010",
                aircraftService.findById(1L)
        );
        flightService.save(flight);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flight);
    }
}
