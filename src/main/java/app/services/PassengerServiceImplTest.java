package app.services;

import app.entities.Passenger;
import app.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImplTest implements PassengerServiceTest{
    private final PassengerRepository passengerRepository;

    private final PassportService passportService;

    public PassengerServiceImplTest(PassengerRepository passengerRepository, PassportService passportService) {
        this.passengerRepository = passengerRepository;
        this.passportService = passportService;
    }

    @Override
    public void save(Passenger passenger) {
        passportService.save(passenger);
        passengerRepository.save(passenger);
    }

    @Override
    public void update(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    @Override
    public void delete(Long id) {
        passengerRepository.deleteById(id);
    }

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger findById(Long id) {
        return passengerRepository.getById(id);
    }
}
