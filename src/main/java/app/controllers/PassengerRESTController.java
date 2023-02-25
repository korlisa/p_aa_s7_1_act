package app.controllers;

import app.entities.Passenger;
import app.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class PassengerRESTController {
    private final PassengerService passengerService;

    @Autowired
    public PassengerRESTController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping()
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        return ResponseEntity.ok(passengerService.getAllPassenger());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassenger(@PathVariable long id) {
        Optional<Passenger> passenger = Optional.ofNullable(passengerService.getPassenger(id));

        return passenger.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Passenger> addNewPassenger(@RequestBody Passenger passenger) {
        passengerService.savePassenger(passenger);
        return ResponseEntity.ok(passenger);
    }

    @PutMapping()
    public ResponseEntity<Passenger> editPassenger(@RequestBody Passenger passenger) {
            return  ResponseEntity.ok(passengerService.update(passenger));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Passenger> deletePassenger(@PathVariable long id) {
        Optional<Passenger> passenger = Optional.ofNullable(passengerService.getPassenger(id));

        if (passenger.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } else {
            passengerService.deletePassenger(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}