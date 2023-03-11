package app.controllers;//package app.controllers;
//
//import app.entities.Passenger;
//import app.entities.User;
//import app.services.PassengerService;
//import app.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.security.Principal;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/user")
//public class PassengerRESTController {
//    private final PassengerService passengerService;
//
//    private final UserService userService;
//
//    @Autowired
//    public PassengerRESTController(PassengerService passengerService, UserService userService) {
//        this.passengerService = passengerService;
//        this.userService = userService;
//    }
//
//    @GetMapping()
//    public ResponseEntity<User> getUser(Principal principal) {
//        return new ResponseEntity<>(userService.findByEmail(principal.getName()), HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Passenger> getPassenger(@PathVariable long id) {
//        Optional<Passenger> passenger = Optional.ofNullable(passengerService.getPassenger(id));
//
//        return passenger.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @PostMapping()
//    public ResponseEntity<Passenger> addNewPassenger(@RequestBody Passenger passenger) {
//        passengerService.savePassenger(passenger);
//        return ResponseEntity.ok(passenger);
//    }
//
//    @PutMapping()
//    public ResponseEntity<Passenger> editPassenger(@RequestBody Passenger passenger) {
//            return  ResponseEntity.ok(passengerService.update(passenger));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Passenger> deletePassenger(@PathVariable long id) {
//        Optional<Passenger> passenger = Optional.ofNullable(passengerService.getPassenger(id));
//
//        if (passenger.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//        } else {
//            passengerService.deletePassenger(id);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//    }
//}