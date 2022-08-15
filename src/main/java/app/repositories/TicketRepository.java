package app.repositories;

import app.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//Репозиторий сущности "Билет"
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    /**
     * added — Alexander Plekhov
     */
    @Query(value = "FROM Ticket t WHERE t.flight.id = ?1")
    List<Ticket> findAllTicketsByFlightId(Long id);

}
