package app.controllers;

import app.entities.Ticket;
import app.services.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Api(tags = "Ticket Rest Controller", description = "CRUD for Ticket")
@RequestMapping("/api/ticket")
public class TicketRESTController {

    /**
     * Field ticketService with CRUD service for Ticket Entity
     *
     * @see TicketService
     */
    Logger logger = LogManager.getLogger(getClass());
    private final TicketService ticketService;

    public TicketRESTController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Method showAllTickets() returns List of all Tickets
     *
     * @see app.services.TicketServiceImpl#getAllTickets()
     */
    @ApiOperation(value = "Get List of all Tickets")
    @GetMapping("/all")
    public List<Ticket> showAllTickets() {
        List<Ticket> allTickets = ticketService.getAllTickets();
        logger.info("Show all tickets");
        return allTickets;
    }

    /**
     * Method getTicket(Long) returns a Ticket by id
     *
     * @see app.services.TicketServiceImpl#getTicket(Long)
     */
    @ApiOperation(value = "Get one Ticket")
    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicket(id);
        if (ticket == null) {
            logger.warn("Ticket not found: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
        }
        logger.info("Show ticket: " + id);
        return ticket;
    }

    /**
     * Method addNewTicket() to create a new Ticket
     *
     * @see app.services.TicketServiceImpl#saveTicket(Ticket)
     */
    @ApiOperation(value = "Create Ticket")
    @PostMapping("/create")
    public Ticket addNewTicket(@RequestBody Ticket ticket) {
        ticketService.saveTicket(ticket);
        logger.info("Ticket created");
        return ticket;
    }

    /**
     * Method update() to update a Ticket
     *
     * @see app.services.TicketServiceImpl#update(Ticket)
     */
    @ApiOperation(value = "Update Ticket")
    @PutMapping("/edit")
    public Ticket edit(@RequestBody Ticket ticket) {
        if (ticketService.getTicket(ticket.getId()) == null) {
            logger.warn("Ticket not found: " + ticket.getId());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
        }
        logger.info("Ticket edited: " + ticket.getId());
        return ticketService.update(ticket);
    }

    /**
     * Method delete(Long) to delete a Ticket by id
     *
     * @see app.services.TicketServiceImpl#deleteTicket(Long)
     */
    @ApiOperation(value = "Delete Ticket")
    @DeleteMapping("/{id}/delete")
    public Ticket delete(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicket(id);
        if (ticket == null) {
            logger.warn("Ticket not found: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
        }
        ticketService.deleteTicket(id);
        logger.info("Ticket deleted: " + id);
        return ticket;
    }
}
