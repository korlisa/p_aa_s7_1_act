package app.controllers;

import app.entities.Booking;
import app.services.BookingService;
import app.services.FlightService;
import app.services.PassengerServiceTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;
    private final PassengerServiceTest passengerServiceTest;
    private final FlightService flightService;

    public BookingController(BookingService bookingService, PassengerServiceTest passengerServiceTest, FlightService flightService) {
        this.bookingService = bookingService;
        this.passengerServiceTest = passengerServiceTest;
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<Booking> create(@RequestBody Booking booking) {
        booking = new Booking(
                555L,
                passengerServiceTest.findById(2L),
                flightService.findById(1L),
                flightService.findById(2L)
        );
        bookingService.save(booking);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(booking);
    }

    @GetMapping
    public List<Booking> getAll() {
        return bookingService.findAll();
    }
}