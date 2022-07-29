package app.services;

import app.entities.Passenger;
import app.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class PassengerServiceImpl with CRUD methods for Passenger
 *
 * @author Tamara Ustyan
 */


@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    /**
     * Method getAllPassengers() returns List of all Passenger
     */
    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    /**
     * Method savePassenger(Passenger) for create Passenger.
     */
    @Override
    public void savePassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    /**
     * Method getPassenger(long) for get Passenger by id
     * return Passenger
     */
    @Override
    public Passenger getPassenger(long id) {
        Passenger passenger = null;
        Optional<Passenger> optional = passengerRepository.findById(id);
        if(optional.isPresent()) {
            passenger = optional.get();
        }
        return passenger;
    }

    /**
     * Method deletePassenger(long) for delete Passenger by id
     */
    @Override
    public void deletePassenger(long id) {
        passengerRepository.deleteById(id);
    }

    /**
     * Method update(Passenger) for update Passenger
     */
    @Override
    public Passenger update(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    /**
     * Method deleteAll() for delete all Passengers
     */
    @Override
    public void deleteAll() {
        passengerRepository.deleteAll();
    }

}
