package app.repositories;

import app.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Репозиторий сущности "Билет"
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
