package app.repositories;


import app.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    Seat findBySeatNumber(Integer seatNumber);

    @Override
    List<Seat> findAll();
}
