package app.services;

import app.entities.Aircraft;

import java.util.List;


public interface AircraftService {
    void save(Aircraft aircraft);

    void update(Aircraft aircraft);

    void delete(Long id);

    Aircraft findById(Long id);

    List<Aircraft> findAll();

    Aircraft findByBoardNumber(String boardNumber);
}
