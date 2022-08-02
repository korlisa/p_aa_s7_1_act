package app.services;

import app.entities.Aircraft;
import app.repositories.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class AircraftServiceImpl with CRUD methods for Aircraft
 *
 * @author Eugene Kolyshev
 */
@Service
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;

    @Autowired
    public AircraftServiceImpl(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    /**
     * Method getAll() to get all Aircraft
     * @return list of Aircraft
     */
    @Override
    public List<Aircraft> getAll() {
        return aircraftRepository.findAll();
    }

    /**
     * Method getById(Long) to get Aircraft by id
     * @return Aircraft
     */
    @Override
    public Aircraft getById(Long id) {
        return aircraftRepository.findById(id).get();
    }

    /**
     * Method save() to create Aircraft
     *
     */
    @Override
    public void save(Aircraft aircraft) {
        aircraftRepository.save(aircraft);
    }

    /**
     * Method update() to update Aircraft
     */
    @Override
    public void update(Aircraft aircraft) {
        aircraftRepository.save(aircraft);
    }

    /**
     * Method removeById() for delete Aircraft by id
     */
    @Override
    public void removeById(Long id) {
        aircraftRepository.deleteById(id);
    }

    /**
     * Method findAircraftByName() to get an Aircraft
     * by side number return Aircraft
     * @return Aircraft
     */
    @Override
    public Aircraft findAircraftByName(String sideNumber) {
        return aircraftRepository.findAircraftByName(sideNumber);
    }

}
