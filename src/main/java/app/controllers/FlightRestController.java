package app.controllers;

import app.entities.Flight;
import app.entities.Seat;
import app.services.FlightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
 * @author Alexander Plekhov
 */

@RestController
@RequestMapping("/api/flights")
@Api(value = "flight", tags = {"flight"})
public class FlightRestController {

    Logger logger = LogManager.getLogger(getClass());
    private final FlightService flightService;

    @Autowired
    public FlightRestController(FlightService flightService) {
        this.flightService = flightService;
    }


    @ApiOperation(value = "return Flight by Id",
                  response = Flight.class,
                  tags = {"flight"})
    @GetMapping("/{id}")
    public ResponseEntity<Flight> findFlightById(@PathVariable("id") long id) {
        Flight flight = flightService.findFlightById(id);
        if (flight == null) {
            logger.warn("Flight not found (findFlightById): " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("show Flight by id: " + id);
        return ResponseEntity.ok(flight);
    }

    @ApiOperation(value = "return all Flights by from/to/date, " +
                                    "Date must be in the format \"yyyy-MM-dd\", example: 1970-01-01",
                  response = Flight.class,
                  responseContainer = "List",
                  tags = {"flight"})
    @GetMapping("/{from}/{to}/{date}")
    public ResponseEntity<List<Flight>> findFlightByFromToDate(@PathVariable("from") String from,
                                                               @PathVariable("to") String to,
                                                               @PathVariable("date") String date) {
        List<Flight> flight = flightService.findFlightByFromToDate(from, to, date);
        if (flight == null) {
            logger.warn("Flight not found (findFlightByFromToDate): " + from + "/" + to + "/" + date);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } logger.info("Flight found: " + from + "/" + to + "/" + date);
        return ResponseEntity.ok(flight);
    }

    @ApiOperation(value = "create a new Flight",
                  tags = {"flight"})
    @PostMapping()
    public ResponseEntity<Flight> createFLight(@RequestBody Flight flight) {
        flightService.save(flight);
        logger.info("Flight created:" + flight.getId());
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }

    @ApiOperation(value = "edit/update FLight",
                  tags = {"flight"})
    @PutMapping()
    public ResponseEntity<Flight> updateFlight(@RequestBody Flight flight) {
        flightService.save(flight);
        logger.info("Flight updated:" + flight.getId());
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @ApiOperation(value = "delete Flight by Id",
                  tags = {"flight"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Flight> deleteFlightById(@PathVariable("id") long id) {
        Flight flight = flightService.findFlightById(id);
        flightService.deleteById(id);
        logger.info("Flight deleted:" + id);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @ApiOperation(value = "getting all free Seats on the Flight",
                  responseContainer = "List",
                  tags = {"flight"})
    @GetMapping("/allFree")
    public ResponseEntity<List<Seat>> findAllFreeSeatsOnFlight(@RequestBody Flight flight) {
        List<Seat> listSeat = flightService.findAllFreeSeatsOnFlight(flight);
        if (listSeat == null) {
            logger.warn("listSeat does not exist (findAllFreeSeatsOnFlight): " + flight.getId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("All free seats on flight found: " + flight.getId());
        return ResponseEntity.ok(listSeat);
    }

    @ApiOperation(value = "getting all free Seats on Flight by Category Economy",
                  responseContainer = "List",
                  tags = {"flight"})
    @GetMapping("/allFreeEconomy")
    public ResponseEntity<List<Seat>> findAllFreeSeatsOnFlightByEconomy(@RequestBody Flight flight) {
        List<Seat> listSeatByEconomy = flightService.findAllFreeSeatsOnFlightByEconomy(flight);
        if (listSeatByEconomy == null) {
            logger.warn("listSeatByEconomy does not exist (findAllFreeSeatsOnFlightByEconomy): " + flight.getId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("All free seats by economy on flight found: " + flight.getId());
        return ResponseEntity.ok(listSeatByEconomy);
    }

    @ApiOperation(value = "getting all free Seats on Flight by Category Business",
                  responseContainer = "List",
                  tags = {"flight"})
    @GetMapping("/allFreeBusiness")
        public ResponseEntity<List<Seat>> findAllFreeSeatsOnFlightByBusiness(@RequestBody Flight flight) {
        List<Seat> listSeatByBusiness = flightService.findAllFreeSeatsOnFlightByBusiness(flight);
        if (listSeatByBusiness == null) {
            logger.warn("listSeatByBusiness does not exist (findAllFreeSeatsOnFlightByBusiness): " + flight.getId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("All free seats by business on flight found: " + flight.getId());
        return ResponseEntity.ok(listSeatByBusiness);
    }
}
