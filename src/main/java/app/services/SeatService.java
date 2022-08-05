package app.services;

import app.entities.Seat;
import java.util.List;


public interface SeatService {


    List<Seat> getAll();

    List<Seat> getAllByFlightId(Long id);

    List<Seat> getAllByFlightIdAndCategory(Long id, String category);

    List<Seat> getAllByFlightIdAndSales(Long id, Boolean isSold);

    List<Seat> getAllByFlightIdAndRegistration(Long id, Boolean isRegistration);

    void save(Seat seat);

    void update(Seat seat);

    Seat getById(Long id);

    void removeById(Long id);

    Seat findSeatBySeatNumber(String seatNumber);

}
