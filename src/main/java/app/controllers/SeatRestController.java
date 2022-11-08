package app.controllers;

import app.entities.Category;
import app.entities.Seat;
import app.services.SeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * REST controller for {@link app.entities.Seat} entity
 *
 * @author Shchepin Maksim
 */

@Api
@RestController
@RequestMapping("/api")
public class SeatRestController {

    private final SeatService seatService;
    @Autowired
    public SeatRestController(SeatService seatService) {
        this.seatService = seatService;
    }

    @ApiOperation("Get a seat by id")
    @GetMapping("/seats/{id}")
    public ResponseEntity<Seat> getSeat(@PathVariable Long id) {
        return seatService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NoSuchElementException("Seat not found with id " + id));
    }

    @ApiOperation("Get all seats: all from seats table")
    @GetMapping("/seats")
    public List<Seat> getAllSeats() {
        return seatService.findAll();
    }

    @ApiOperation("Create new seat")
    @PostMapping("/seats")
    public ResponseEntity<Seat> addSeat(@RequestBody Seat seat) {
        return ResponseEntity.ok(seatService.save(seat));
    }

    @ApiOperation("Update seat parameters")
    @PutMapping("/seats")
    public ResponseEntity<Seat> updateSeat(
            @RequestBody Seat seat) {
        return ResponseEntity.ok(seatService.update(seat));
    }

    @ApiOperation("Delete a seat by id")
    @DeleteMapping("/seats/{id}")
    public boolean deleteSeat(@PathVariable Long id) {
        return seatService.deleteById(id);
    }

    @ApiOperation("Delete all seats: clear seats table")
    @DeleteMapping("/seats")
    public void deleteAllSeats() {
        seatService.deleteAll();
    }

    @ApiOperation("Get all seats by flight id")
    @GetMapping("/seats/flight/{id}")
    public List<Seat> getAllSeatsByFlightId(@PathVariable Long id) {
        return seatService.findAllByFlightId(id);
    }

    @ApiOperation("Get all seats by flight id and category")
    @GetMapping("/seats/category/{category}/flight/{id}")
    public List<Seat> getSeatByFlightId(@PathVariable Category category, @PathVariable Long id) {
        return seatService.findAllByFlightIdAndCategory(id, category);
    }

    @ApiOperation("Get number of sold seats on the flight by id")
    @GetMapping("/seats/sold/flight/{id}")
    public int getSeatsSoldCountByFlightId(@PathVariable Long id) {
        return seatService.getSoldByFlightId(id);
    }

    @ApiOperation("Get number of unsold seats on the flight by id")
    @GetMapping("/seats/unsold/flight/{id}")
    public int getSeatsUnsoldCountByFlightId(@PathVariable Long id) {
        return seatService.getUnsoldByFlightId(id);
    }

    @ApiOperation("Get number of registered seats on the flight by id")
    @GetMapping("/seats/registered/flight/{id}")
    public int getSeatsRegCountByFlightId(@PathVariable Long id) {
        return seatService.getRegisteredByFlightId(id);
    }

}
