package app.services;

import app.entities.Flight;

import java.util.List;

public interface FlightService {
    void save(Flight flight);

    void update(Flight flight);

    void delete(Long id);

    List<Flight> findAll();

    Flight findById(Long id);
}
