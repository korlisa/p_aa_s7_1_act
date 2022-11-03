package app.services;

import app.entities.Ticket;

import java.util.List;

public interface TicketService {
    void createTicket(Ticket ticket);

    List<Ticket> getAllTicket();

    Ticket getTicketById(Long id);

    void deleteTicket(Long id);

    void editTicket(Ticket ticket);
}
