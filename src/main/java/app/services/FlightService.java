package app.services;

import app.entities.Destination;
import app.entities.Flight;
import app.entities.Seat;

import java.util.List;

/**
 * Flight Service
 *
 * @author - Babkin Artyom
 */

public interface FlightService {
    void createFlight(Flight flight);
    void updateFlight(Flight flight);
    Flight findFlightById(Long id);
    List<Flight> findFlightByFromToDate(String from, String to, String date);
    List<Seat> findAllFreeSeatsOnFlight(Flight flight);
    List<Seat> findAllFreeSeatsOnFlightByEconomy(Flight flight);
    List<Seat> findAllFreeSeatsOnFlightByBusiness(Flight flight);
}
