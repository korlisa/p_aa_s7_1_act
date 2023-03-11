package app.services;

import app.entities.Passenger;

import java.util.List;

public interface PassengerServiceTest {
    void save(Passenger passenger);

    void update(Passenger passenger);

    void delete(Long id);

    List<Passenger> findAll();

    Passenger findById(Long id);

}
