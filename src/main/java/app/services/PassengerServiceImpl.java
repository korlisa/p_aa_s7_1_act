package app.services;

import app.entities.Passenger;
import app.repositories.PassengerRepository;
import app.entities.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final RoleService roleService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public PassengerServiceImpl(PassengerRepository passengerRepository,
                                RoleService roleService,
                                UserService userService) {
        this.passengerRepository = passengerRepository;
        this.roleService = roleService;
        this.userService = userService;
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
        passenger.setPassword(passwordEncoder.encode(passenger.getPassword()));
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
     *
     * extends - Alexander Plekhov
     *
     */
    @Override
    public Passenger update(Passenger passenger) {
        passenger.addRoleToCollection(roleService.findRoleByName("ROLE_PASSENGER"));

        if (!(passenger.getEmail().equals(SecurityContextHolder.getContext().getAuthentication().getName()))) {
            Authentication auth = new UsernamePasswordAuthenticationToken(passenger, passenger.getPassword(), passenger.getRoles());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        if (passenger.getPassword().length() <= 20) {
            passenger.setPassword(passwordEncoder.encode(passenger.getPassword()));
        }
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

