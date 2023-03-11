package app.controllers;

import app.entities.Seat;
import app.entities.Ticket;
import app.services.AircraftService;
import app.services.BookingService;
import app.services.PassengerServiceTest;
import app.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final BookingService bookingService;
    private final AircraftService aircraftService;
    private final PassengerServiceTest passengerServiceTest;

    public TicketController(TicketService ticketService, BookingService bookingService, AircraftService aircraftService, PassengerServiceTest passengerServiceTest) {
        this.ticketService = ticketService;
        this.bookingService = bookingService;
        this.aircraftService = aircraftService;
        this.passengerServiceTest = passengerServiceTest;
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
        List<Seat> list = aircraftService.findById(1L).getSeats();
        Seat free = null;
        for (Seat seat : list) {
            if (!seat.isRegistered()) {
                seat.setRegistered(true);
                free = seat;
                break;

            }
        }
        ticket = new Ticket (
                bookingService.findById(1L).getBookingNumber(),
                bookingService.findById(1L).getDepartureFlight(),
                free,
                passengerServiceTest.findById(2L)

        );
        ticketService.save(ticket);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ticket);
    }

    @GetMapping
    public List<Ticket> findAll() {
        return ticketService.findAll();
    }
}
