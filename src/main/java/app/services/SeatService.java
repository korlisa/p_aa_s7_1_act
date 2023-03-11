package app.services;

import app.entities.Aircraft;
import app.entities.Seat;

import java.util.List;

public interface SeatService {
    Seat save(Aircraft aircraft, Integer counter);

    void update(Seat seat);

    void delete(Long id);

    Seat findByNumberOfSeats(Integer numberSeat);

    List<Seat> findAll();

    void change();
}
