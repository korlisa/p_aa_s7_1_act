package app.controllers;

import app.entities.Flight;
import app.entities.Seat;
import app.services.FlightService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest-Controller Flight, includes:
 * CRUD
 * getting Flight by ID;
 * getting Flight by from/to/date;
 * getting all free Seats on the Flight
 * getting free Seats on the Flight by Category Economy
 * getting free Seats on the Flight by Category Business
 *
 * @author Babkin Artem
 */

@RestController
@RequestMapping("/api/flights")
public class FlightRestController {

    private final FlightService flightService;
    public FlightRestController(FlightService flightService) {
        this.flightService = flightService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Flight> findFlightById(@PathVariable("id") long id) {
        Flight flight = flightService.findFlightById(id);
        if (flight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(flight);
    }

    @PostMapping()
    public ResponseEntity<Flight> createFLight(@RequestBody Flight flight) {
        flightService.createFlight(flight);
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Flight> updateFlight(@RequestBody Flight flight) {
        flightService.updateFlight(flight);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @GetMapping("/{from}/{to}/{date}")
    public ResponseEntity<List<Flight>> findFlightByFromToDate(@PathVariable("from") String from,
                                                               @PathVariable("to") String to,
                                                               @PathVariable("date") String date) {
        List<Flight> flight = flightService.findFlightByFromToDate(from, to, date);
        if (flight.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(flight);
    }
    @GetMapping("/allFree")
    public ResponseEntity<List<Seat>> findAllFreeSeatsOnFlight(@RequestBody Flight flight) {
        List<Seat> listSeat = flightService.findAllFreeSeatsOnFlight(flight);
        if (listSeat.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(listSeat);
    }

    @GetMapping("/allFreeEconomy")
    public ResponseEntity<List<Seat>> findAllFreeSeatsOnFlightByEconomy(@RequestBody Flight flight) {
        List<Seat> listSeatByEconomy = flightService.findAllFreeSeatsOnFlightByEconomy(flight);
        if (listSeatByEconomy.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(listSeatByEconomy);
    }

    @GetMapping("/allFreeBusiness")
    public ResponseEntity<List<Seat>> findAllFreeSeatsOnFlightByBusiness(@RequestBody Flight flight) {
        List<Seat> listSeatByBusiness = flightService.findAllFreeSeatsOnFlightByBusiness(flight);
        if (listSeatByBusiness.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(listSeatByBusiness);
    }

}
