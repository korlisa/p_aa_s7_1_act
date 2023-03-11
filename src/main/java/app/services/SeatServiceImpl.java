package app.services;


import app.entities.Aircraft;
import app.entities.Seat;
import app.repositories.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public Seat save(Aircraft aircraft, Integer counter) {
        return seatRepository.save(new Seat(counter, 3400, false, false, aircraft));
    }

    @Override
    public void update(Seat seat) {
        if (seatRepository.existsById(seat.getId())) {
            seatRepository.save(seat);
        }
    }

    @Override
    public void delete(Long id) {
        seatRepository.deleteById(id);
    }

    @Override
    public Seat findByNumberOfSeats(Integer numberSeat) {
        return seatRepository.findBySeatNumber(numberSeat);
    }

    @Override
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public void change() {

    }
}

