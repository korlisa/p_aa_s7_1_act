package app.services;

import app.entities.Flight;
import app.entities.Seat;
import app.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation FlightService - Application Business Logic
 *
 * @author - Alexander Plekhov
 */


@Service
public class FLightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FLightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    @Transactional
    public void save(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    @Transactional(readOnly = true)
    public Flight findFlightById(long id) {
        return flightRepository.findFlightById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Flight> findFlightByFromToDate(String from, String to, String date) {
        List<Flight> listWithFromTo = flightRepository.findAllFlightByFromAndTo(from, to);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

        return listWithFromTo.stream()
                .filter(x -> x.getDepartureDateTime().format(formatter).equals(date))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        flightRepository.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Seat> findAllFreeSeatsOnFlight(Flight flight) {
        return flight.getAircraft().getSeats().stream()
                .filter(s -> s.getIsRegistered().equals(false) && s.getIsSold().equals(false)) //changed methods
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Seat> findAllFreeSeatsOnFlightByEconomy(Flight flight) {
        return flight.getAircraft().getSeats().stream()
                .filter(s -> s.getCategory().toString().equals("Economy"))
                .filter(s -> s.getIsRegistered().equals(false) && s.getIsSold().equals(false)) //changed methods
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Seat> findAllFreeSeatsOnFlightByBusiness(Flight flight) {
        return flight.getAircraft().getSeats().stream()
                .filter(s -> s.getCategory().toString().equals("Business"))
                .filter(s -> s.getIsRegistered().equals(false) && s.getIsSold().equals(false)) //changed methods
                .collect(Collectors.toList());
    }
}
