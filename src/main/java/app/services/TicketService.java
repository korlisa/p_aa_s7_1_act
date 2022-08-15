package app.services;

import app.entities.Ticket;

import java.util.List;

//Интерфейс для билета
public interface TicketService {

    List<Ticket> getAllTickets();

    void saveTicket(Ticket ticket);

    Ticket getTicket(Long id);

    void deleteTicket(Long id);

    Ticket update(Ticket ticket);

    void deleteAllTickets();

    List<Ticket> findAllTicketsByFlightId(Long id);

}
