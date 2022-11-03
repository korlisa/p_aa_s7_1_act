package app.services;

import app.entities.Ticket;
import app.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class TicketServiceImpl with CRUD methods for Ticket
 *
 * @author Darya Shonoeva
 */

@Service
public class TicketServiceImpl implements TicketService {


    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void createTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getAllTicket() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.getById(id);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);

    }

    @Override
    public void editTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }
}
