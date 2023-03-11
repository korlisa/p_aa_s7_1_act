package app.services;

import app.entities.Destination;

import java.util.List;

public interface DestinationService {
    void save(Destination destination);

    void update(Destination destination);

    void delete(Long id);

    List<Destination> findAll();

    Destination findById(Long id);
}
