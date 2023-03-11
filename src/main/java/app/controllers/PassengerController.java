package app.controllers;

import app.entities.Passenger;
import app.services.PassengerServiceTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    private final PassengerServiceTest passengerServiceTest;

    public PassengerController(PassengerServiceTest passengerServiceTest) {
        this.passengerServiceTest = passengerServiceTest;
    }

    @GetMapping
    public List<Passenger> getList() {
        return passengerServiceTest.findAll();
    }


    @PostMapping
    public ResponseEntity<Passenger> create(@RequestBody Passenger passenger) {
        passenger = new Passenger("81112223344", "mixa@mail.ru");
        passengerServiceTest.save(passenger);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(passenger);
    }

//    @GetMapping("/{id}")
//    public Aircraft getById(@PathVariable Long id) {
//        Passenger passenger = passengerServiceTest.findById(id);
//        return passenger;
//    }

//    @GetMapping("/{boardNumber}")
//    public Aircraft getByBoardBumber(@PathVariable String boardNumber) {
//        return aircraftServiceTest.findByBoardNumber(boardNumber);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        passengerServiceTest.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("The user has been successfully deleted");
    }
}
