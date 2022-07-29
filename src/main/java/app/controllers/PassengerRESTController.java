package app.controllers;

import app.entities.Passenger;
import app.services.PassengerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//REST-Controller сущности "Пассажир"

@RestController
@Api(tags = "Passenger Rest Controller", description = "CRUD for Passenger")
@RequestMapping("/api/passenger")
public class PassengerRESTController {

    /**
     * Field passengerService with CRUD service for Passenger Entity
     *
     * @see PassengerService
     */

    private final PassengerService passengerService;

    public PassengerRESTController(PassengerService passengerService) {
        this.passengerService = passengerService;
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger not found");
        }
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
        if ( passengerService.getPassenger(passenger.getId()) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger not found");
        }
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger not found");
        }
        passengerService.deletePassenger(id);
        return passenger;
    }


  /*  @DeleteMapping("/deleteAll")
    public void deleteAll() {
        passengerService.deleteAll();
    }*/
}
