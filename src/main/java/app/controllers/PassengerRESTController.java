package app.controllers;

import app.entities.Passenger;
import app.services.PassengerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import app.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//REST-Controller сущности "Пассажир"

@RestController
@Api(tags = "Passenger Rest Controller", description = "CRUD for Passenger")
@RequestMapping("/api/passenger")
public class PassengerRESTController {

    Logger logger = LogManager.getLogger(getClass());

    /**
     * Field passengerService with CRUD service for Passenger Entity
     *
     * @see PassengerService
     */

    private final PassengerService passengerService;
    private final UserService userService;

    public PassengerRESTController(PassengerService passengerService, UserService userService) {
        this.passengerService = passengerService;
        this.userService = userService;
    }

    //Получить всех пассажиров

    /**
     * Method showAllPassengers() returns List of all Passenger
     *
     * @see app.services.PassengerServiceImpl#getAllPassengers()
     */

    @ApiOperation(value = "Get List of all Passengers")
    @GetMapping("/all")
    public List<Passenger> showAllPassengers() {
        List<Passenger> allPassengers = passengerService.getAllPassengers();
        logger.info("show all Passengers ");
        return allPassengers;
    }

    //Получить пассажира по id
    /**
     * Method getPassenger(Long) returns a Passenger by id
     *
     * @see app.services.PassengerServiceImpl#getPassenger(long)
     */

    @ApiOperation(value = "Get one Passenger")
    @GetMapping("/{id}")
    public Passenger getPassenger(@PathVariable long id) {
        Passenger passenger = passengerService.getPassenger(id);
        if (passenger == null) {
            logger.warn("Passenger not found (getPassenger): " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger not found");
        }
        logger.info("Show Passenger: " + id);
        return passenger;
    }

    //Создать нового пассажира (создается сразу с паспортом)
    /**
     * Method addNewPassenger() to create a new Passenger
     *
     * @see app.services.PassengerServiceImpl#savePassenger(Passenger)
     */

    @ApiOperation(value = "Create Passenger")
    @PostMapping("/create")
    public Passenger addNewPassenger(@RequestBody Passenger passenger) {
        passengerService.savePassenger(passenger);
        logger.info("Passenger created: " + passenger.getId());
        return passenger;
    }

    //Изменить пассажира
    /**
     * Method edit() to update a Passenger
     *
     * @see app.services.PassengerServiceImpl#update(Passenger)
     */

    @ApiOperation(value = "Update Passenger")
    @PutMapping("/edit")
    public Passenger edit(@RequestBody Passenger passenger) {
        if (passengerService.getPassenger(passenger.getId()) == null) {
            logger.warn("Passenger does not exist: " + passenger.getId());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger not found");
        }
        logger.info("Passenger updated: " + passenger.getId());
        return passengerService.update(passenger);
    }

    //Удалить пассажира
    /**
     * Method delete(long) to delete a Passenger by id
     *
     * @see app.services.PassengerServiceImpl#deletePassenger(long)
     */
    @ApiOperation(value = "Delete Passenger")
    @DeleteMapping("/{id}/delete")
    public Passenger delete(@PathVariable long id) {
        Passenger passenger = passengerService.getPassenger(id);
        if (passenger == null) {
            logger.warn("Passenger does not exist: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger not found");
        }
        passengerService.deletePassenger(id);
        logger.info("Passenger deleted: " + id);
        return passenger;
    }

    /**
     * getting current User (Passenger)
     *
     * @author Alexander Plekhov
     */
    @ApiOperation(value = "getting current User")
    @GetMapping("/current")
    public ResponseEntity<Passenger> getCurrentUser() {
        String passengerEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Passenger passenger = userService.findPassengerByEmail(passengerEmail);
        return ResponseEntity.ok(passenger);
    }

  /*  @DeleteMapping("/deleteAll")
    public void deleteAll() {
        passengerService.deleteAll();
    }*/
}
