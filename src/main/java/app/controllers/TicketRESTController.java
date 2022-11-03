package app.controllers;

import app.entities.Ticket;
import app.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tickets")
public class TicketRESTController {

    private final TicketService ticketService;

    @Autowired
    public TicketRESTController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createNewTicket(@RequestBody Ticket ticket) {
        ticketService.createTicket(ticket);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        return ticket != null
                ? new ResponseEntity<>(ticket, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<Ticket>> getListTickets() {
        List<Ticket> tickets = ticketService.getAllTicket();
        return tickets != null
                ? new ResponseEntity<>(tickets, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTicketById(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> editTicket(@RequestBody Ticket ticket) {
        ticketService.editTicket(ticket);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
