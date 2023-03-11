package app.services;

import app.entities.Ticket;

import java.util.List;

public interface TicketService {
    void save(Ticket ticket);

    void update(Ticket ticket);

    void delete(Long id);

    List<Ticket> findAll();

    Ticket findById(Long id);
}
