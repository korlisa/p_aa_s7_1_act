package app.services;

import app.entities.Ticket;

import java.util.List;

//Интерфейс для билета
public interface TicketService {

    public List<Ticket> getAllTickets();

    public void saveTicket(Ticket ticket);

    public Ticket getTicket(Long id);

    public void deleteTicket(Long id);

    public Ticket update(Ticket ticket);

    void deleteAllTickets();
}
