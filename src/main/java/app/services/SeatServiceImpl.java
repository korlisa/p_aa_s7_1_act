package app.services;

import app.entities.Category;
import app.entities.Seat;
import app.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;



@Service
@Transactional
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final FlightService flightService;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository, FlightService flightService) {
        this.seatRepository = seatRepository;
        this.flightService = flightService;
    }

    @Override
    public Optional<Seat> findById(Long id) {
        return seatRepository.findById(id);
    }

    @Override
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public Seat update(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public boolean deleteById(Long id) {
        if (seatRepository.existsById(id)) {
            seatRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        seatRepository.deleteAll();
    }

    //------------------------------ next methods will be finalized when I got flightService & Category-----------------
    @Override
    public List<Seat> findAllByFlightId(Long id) {
        return null;
    }

    @Override
    public List<Seat> findAllByFlightIdAndCategory(Long id, Category category) {
        return null;
    }

    @Override
    public int getSoldByFlightId(Long id) {
        return 0;
    }

    @Override
    public int getUnsoldByFlightId(Long id) {
        return 0;
    }

    @Override
    public int getRegisteredByFlightId(Long id) {
        return 0;
    }
}
