package app.services;

import app.entities.Baggage;
import app.entities.Booking;

import java.util.List;

public interface BaggageService {

    Baggage save(Booking booking);

    void update(Baggage baggage);

    void delete(Long id);

    List<Baggage> findAll();

    Baggage findById(Long id);
}
