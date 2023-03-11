package app.services;

import app.entities.Booking;

import java.util.List;

public interface BookingService {
    void save(Booking booking);

    void update(Booking booking);

    void delete(Long id);

    List<Booking> findAll();

    Booking findById(Long id);
}
