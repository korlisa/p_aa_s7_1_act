package app.controllers;

import app.entities.Passenger;
import app.services.PassengerService;
import app.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/passenger")


public class PassengerRESTController {
    private final PassengerService passengerService;
    private final UserService userService;

    public PassengerRESTController(PassengerService passengerService, UserService userService) {
        this.passengerService = passengerService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<Passenger> showAllPassengers() {
        List<Passenger> allPassengers = passengerService.getAllPassenger();
        return allPassengers;
    }

    @GetMapping("/{id}")
    public Passenger getPassenger(@PathVariable long id) {
        Passenger passenger = passengerService.getPassenger(id);
        return passenger;
    }

    @PostMapping("/create")
    public Passenger addNewPassenger(@RequestBody Passenger passenger) {
        passengerService.savePassenger(passenger);
        return passenger;
    }

    @PutMapping("/edit")
    public Passenger edit(@RequestBody Passenger passenger) {
            return passengerService.update(passenger);
        }

    @DeleteMapping("/{id}/delete")
    public Passenger delete(@PathVariable long id) {
        Passenger passenger = passengerService.getPassenger(id);
        passengerService.deletePassenger(id);
        return passenger;
    }
}