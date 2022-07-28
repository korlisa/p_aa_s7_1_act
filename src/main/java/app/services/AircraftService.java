package app.services;

import app.entities.Aircraft;
import java.util.List;

public interface AircraftService {

    List<Aircraft> getAll();

    Aircraft getById(Long id);

    void save(Aircraft aircraft);

    void update(Aircraft aircraft);

    void removeById(Long id);

    Aircraft findAircraftByName(String sideNumber);


}
