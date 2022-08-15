package app.services;

import app.entities.Ticket;
import app.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * Class TicketServiceImpl with CRUD methods for Ticket
 *
 * @author Tamara Ustyan
 */

@AllArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    /**
     * Method getAllTickets() returns List of all Tickets
     */
    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    /**
     * Method saveTicket(Ticket) for create Ticket.
     */
    @Override
    public void saveTicket(Ticket ticket) {
        ticket.setBookingNumber(String.valueOf(ticket.getFlight().getId() + "/" + ticket.getPassenger().getId()));
        ticketRepository.save(ticket);
    }


    /**
     * Method getTicket(long) for get Ticket by id
     * return Ticket
     */
    @Override
    public Ticket getTicket(Long id) {
        Ticket ticket = null;
        Optional<Ticket> optional = ticketRepository.findById(id);
        if(optional.isPresent()) {
            ticket = optional.get();
        }
        return ticket;
    }

    /**
     * Method deleteTicket(long) for delete Ticket by id
     */
    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
    /**
     * Method update(Ticket) for update Ticket
     */
    @Override
    public Ticket update(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    /**
     * Method deleteAll() for delete all Tickets
     */
    @Override
    public void deleteAllTickets() {
        ticketRepository.deleteAll();
    }

    /**
     * added — Alexander Plekhov
     */
    @Override
    public List<Ticket> findAllTicketsByFlightId(Long id) {
        return ticketRepository.findAllTicketsByFlightId(id);
    }

}
