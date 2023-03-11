package app.services;

import app.entities.Passenger;
import app.entities.Passport;

public interface PassportService {
    Passport save(Passenger passenger);

    void update(Passport passport);

    void delete(Long id);
}
