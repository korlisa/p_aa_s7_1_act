package app.controllers;

import app.entities.Seat;
import app.services.SeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controllers for Seat entity
 *
 * @author Eugene Kolyshev
 * @see app.entities.Seat
 * @see app.services.SeatService
 */
@Api(value = "Seat Rest Controller", tags = "seat")
@RestController
@RequestMapping("/api/seats")
public class SeatRestController {

    Logger logger = LogManager.getLogger(getClass());

    private final SeatService seatService;

    @Autowired
    public SeatRestController(SeatService seatService) {
        this.seatService = seatService;
    }

    /**
     * Method getAll() to get list of all seats in airline company
     *
     * @return List of Seat
     */
    @ApiOperation(value = "Get List of all Seats in airline company",
            response = Seat.class, responseContainer = "List")
    @GetMapping()
    private ResponseEntity<List<Seat>> getAll() {
        final List<Seat> seats = seatService.getAll();
        if(seats == null) {
            logger.warn("List of Seats not found");
        } else {
            logger.info("Show List of Seats");
        }
        return seats != null && !seats.isEmpty()
                ? new ResponseEntity<>(seats, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method getAllByFlightId() to get list of seats by flight_id
     *
     * @param id flight_id
     * @return List of Seat
     */
    @ApiOperation(value = "Get List of all Seats by flight_id",
            response = Seat.class, responseContainer = "List")
    @GetMapping("/flight/{id}")
    private ResponseEntity<List<Seat>> getAllByFlightId(@PathVariable("id") Long id) {
        final List<Seat> seats = seatService.getAllByFlightId(id);
        if(seats == null) {
            logger.warn("List of Seats by flight_id not found: " + id);
        } else {
            logger.info("Show List of Seats by flight_id");
        }
        return seats != null && !seats.isEmpty()
                ? new ResponseEntity<>(seats, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    /**
     * Method getAllByFLightIdAndCategory() to get list of seats by flight_id and category
     *
     * @param id       flight_id
     * @param category category of the seat: "Business" or "Economy"
     * @return List of Seat
     */
    @ApiOperation(value = "Get List of all Seats by flight_id & Category",
            response = Seat.class, responseContainer = "List")
    @GetMapping("/flight/{id}/category/{category}")
    private ResponseEntity<List<Seat>> getAllByFLightIdAndCategory(@PathVariable("id") Long id,
                                                                   @PathVariable("category") String category) {

        final List<Seat> seats = seatService.getAllByFlightIdAndCategory(id, category);
        if(seats == null) {
            logger.warn("List of all Seats by flight_id & Category not found: " + id + "/" + category);
        } else {
            logger.info("Show List of all Seats by flight_id & Category" + id + "/" + category);
        }
        return seats != null && !seats.isEmpty()
                ? new ResponseEntity<>(seats, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method getAllByFlightIdAndSales() to get list of seats by flight_id & sales
     *
     * @param id     flight_id
     *
     * @return List of Seat
     */
    @ApiOperation(value = "Get a list of all seats by flight_id and sales",
            response = Seat.class, responseContainer = "List")
    @GetMapping("/flight/{id}/isSold/{isSold}")
    private ResponseEntity<List<Seat>> getAllByFlightIdAndSales(@PathVariable("id") Long id,
                                                                @PathVariable("isSold") Boolean isSold) {
        final List<Seat> seats = seatService.getAllByFlightIdAndSales(id, isSold);
        if(seats == null) {
            logger.warn("list of all seats by flight_id and sales not found: " + id + "/" + isSold);
        } else {
            logger.info("Show list of all seats by flight_id and sales: " + id + "/" + isSold);
        }
        return seats != null && !seats.isEmpty()
                ? new ResponseEntity<>(seats, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method getAllByFlightIdAndRegistration() to get list of seats by flight_id & registration
     *
     * @param id           flight_id
     * @param isRegistered is registered or not passenger on this seat: true or false
     * @return List of Seat
     */
    @ApiOperation(value = "Get a list of all seats by flight_id and registration",
            response = Seat.class, responseContainer = "List")
    @GetMapping("/flight/{id}/isRegistered/{isRegistered}")
    private ResponseEntity<List<Seat>> getAllByFlightIdAndRegistration(@PathVariable("id") Long id,
                                                                       @PathVariable("isRegistered") Boolean isRegistered) {
        final List<Seat> seats = seatService.getAllByFlightIdAndRegistration(id, isRegistered);
        if(seats == null) {
            logger.warn("list of all seats by flight_id and registration not found: " + id + "/" + isRegistered);
        } else {
            logger.info("Show list of all seats by flight_id and registration: " + id + "/" + isRegistered);
        }
        return seats != null && !seats.isEmpty()
                ? new ResponseEntity<>(seats, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /**
     * Method getOne() to get seat by seat_id
     *
     * @param id seat_id
     * @return Seat
     */
    @ApiOperation(value = "Get one Seat by seat_id", response = Seat.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        final Seat seat = seatService.getById(id);
        if(seat == null) {
            logger.warn("Seat by seat_id not found: " + id);
        } else {
            logger.info("Show Seat by seat_id: " + id);
        }
        return seat != null
                ? new ResponseEntity<>(seat, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method update() to update seat
     *
     * @param seat seat for update
     * @return Seat
     */
    @ApiOperation(value = "Update Seat", response = Seat.class)
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Seat seat) {
        seatService.update(seat);
        logger.info("Seat updated");
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }


}
