package app.controllers;

import app.dto.FlightDto;
import app.entities.Destination;
import app.entities.Flight;
import app.services.DestinationService;
import app.services.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;
    private final DestinationService destinationService;

    @GetMapping("/get_all")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id){
        return flightService.getFlightById(id);
    }

    @PostMapping("/create_flight")
    public ResponseEntity<HttpStatus> createFlight(@RequestBody FlightDto flightDto) {

        ResponseEntity<HttpStatus> responseEntity = new ResponseEntity<>(HttpStatus.CREATED);

        Destination from = destinationService.getDestinationById(flightDto.getFrom().getId());
        Destination to = destinationService.getDestinationById(flightDto.getTo().getId());

        Flight flight = new Flight();
        flight.setFrom(from);
        flight.setTo(to);

        flightService.save(flight);

        return responseEntity;
    }

    @DeleteMapping("delete_flight/{id}")
    public ResponseEntity<HttpStatus> deleteFlight(@PathVariable Long id) {
        ResponseEntity<HttpStatus> responseEntity = new ResponseEntity<>(HttpStatus.OK);

        flightService.deleteFlight(id);

        return responseEntity;
    }

}
