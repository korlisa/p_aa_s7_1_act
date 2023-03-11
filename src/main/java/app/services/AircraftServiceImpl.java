package app.services;

import app.entities.Aircraft;
import app.repositories.AircraftRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;

    private final SeatService seatServiceTest;

    public AircraftServiceImpl(AircraftRepository aircraftRepository, SeatService seatServiceTest) {
        this.aircraftRepository = aircraftRepository;
        this.seatServiceTest = seatServiceTest;
    }

    @Override
    @Transactional
    public void save(Aircraft aircraft) {
        int counter = 1;
        while (counter <= aircraft.getNUMBER_OF_SEATS()) {
            seatServiceTest.save(aircraft, counter);
            counter++;
        }
        aircraftRepository.save(aircraft);
    }

    @Override
    @Transactional
    public void update(Aircraft aircraft) {
        if (aircraftRepository.existsById(aircraft.getId())) {
            save(aircraft);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        aircraftRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Aircraft findById(Long id) {
        return aircraftRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Aircraft> findAll() {
        return aircraftRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Aircraft findByBoardNumber(String boardNumber) {
        return aircraftRepository.findByBoardNumber(boardNumber);
    }
}
