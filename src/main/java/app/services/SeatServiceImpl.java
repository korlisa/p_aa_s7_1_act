package app.services;

import app.entities.Seat;
import app.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class SeatServiceImpl with methods for Seat entity
 *
 * @author Eugene Kolyshev
 */
@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final FlightService flightService;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository, FlightService flightService) {
        this.seatRepository = seatRepository;
        this.flightService = flightService;
    }
    /**
     * Method getAll() to get list of seats from DB
     *
     * @return List of all seats in airline company
     */
    @Override
    public List<Seat> getAll() {
        return seatRepository.findAll();
    }

    /**
     * Method getAll() to get list of seats from DB
     *
     * @param id flight_id
     * @return List of all seats by flight_id
     */
    @Override
    public List<Seat> getAllByFlightId(Long id) {
        return new ArrayList<>(flightService.findFlightById(id).getAircraft().getSeats());
    }

    /**
     * Method getAll() to get list of seats from DB
     *
     * @param id       flight_id
     * @param category Category of the seat
     * @return List of all seats by flight_id and category
     * @value "Business" or "Economy"
     */
    @Override
    public List<Seat> getAllByFlightIdAndCategory(Long id, String category) {
        return flightService.findFlightById(id).getAircraft().getSeats().stream()
                .filter(s -> s.getCategory().toString().equals(category))
                .collect(Collectors.toList());
    }

    /**
     * Method getAll() to get list of seats from DB
     *
     * @param id     flight_id
     *
     * @return List of all seats by flight_id & sales
     */
    @Override
    public List<Seat> getAllByFlightIdAndSales(Long id, Boolean isSold) {
        List<Seat> seats = flightService.findFlightById(id).getAircraft().getSeats().stream()
                .filter(s -> s.getIsSold().equals(false))
                .collect(Collectors.toList());
        return seats;
    }

    /**
     * Method getAllByRegistration() to get list of seats from DB
     *
     * @param id           flight_id
     * @param isRegistered field to know is registered or not passenger on this seat (true or false)
     * @return List of all seats by flight_id & registration
     */
    @Override
    public List<Seat> getAllByFlightIdAndRegistration(Long id, Boolean isRegistered) {
        return flightService.findFlightById(id).getAircraft().getSeats().stream()
                .filter(s -> s.getIsSold().equals(isRegistered))
                .collect(Collectors.toList());
    }

    /**
     * Method save() to save seat to DB
     *
     * @param seat seat for save
     * @return updated seat
     */
    @Override
    public void save(Seat seat) {
        seatRepository.save(seat);
    }

    /**
     * Method update() to update seat from DB
     *
     * @param seat seat for update
     * @return updated seat
     */
    @Override
    public void update(Seat seat) {
        seatRepository.save(seat);
    }

    /**
     * Method getById() to get seat from DB
     *
     * @param id seat_id
     * @return seat by seat_id
     */
    @Override
    public Seat getById(Long id) {
        return seatRepository.findById(id).get();
    }

    @Override
    public void removeById(Long id) {
        seatRepository.deleteById(id);
    }

    @Override
    public Seat findSeatBySeatNumber(String seatNumber) {
        return seatRepository.findSeatBySeatNumber(seatNumber);
    }
}
