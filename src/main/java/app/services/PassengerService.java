package app.services;

import app.entities.Passenger;

import java.util.List;

//Интерфейс сервиса для пассажира

public interface PassengerService {

    List<Passenger> getAllPassengers();

    void savePassenger(Passenger passenger);

    Passenger getPassenger(long id);

    void deletePassenger(long id);

    Passenger update(Passenger passenger);

    void deleteAll();
}
