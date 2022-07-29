package app.services;

import app.entities.Passenger;

import java.util.List;

//Интерфейс сервиса для пассажира

public interface PassengerService {

    public List<Passenger> getAllPassengers();

    public void savePassenger(Passenger passenger);

    public Passenger getPassenger(long id);

    public void deletePassenger(long id);

    public Passenger update(Passenger passenger);

    void deleteAll();
}
