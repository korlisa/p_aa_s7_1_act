package app.services;

import app.entities.*;
import app.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchResultServiceImpl implements SearchResultService {

    private final FlightRepository flightRepository;


    @Autowired
    public SearchResultServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }




    @Override
    public SearchResult getSearchResult(Search search) {
        SearchResult searchResult = new SearchResult();
        List<List<Flight>> listSearch = new ArrayList<>();
        for (Route r : search.getRoutes()) {
            Destination from = r.getFrom();
            Destination to = r.getTo();
            LocalDate departureDate = r.getDepartureDate();

            List<Flight> flightFrom;
            List<Flight> flightFromTo;
            List<Flight> flightFromToDate = null;
            flightFrom = flightRepository.findAll().stream()
                    .filter(f -> (f.getFrom().getCity()).equals(from.getCity())).collect(Collectors.toList());
            if (flightFrom != null) {
                flightFromTo = flightFrom.stream()
                        .filter(f -> (f.getTo().getCity()).equals(to.getCity())).collect(Collectors.toList());

                if (flightFromTo != null) {
                    flightFromToDate = flightFromTo.stream()
                            .filter(f -> (f.getDepartureDateTime().toLocalDate()).equals(departureDate))
                            .collect(Collectors.toList());
                    Collections.sort(flightFromToDate, Comparator.comparing(Flight::getDepartureDateTime));
                }
            }
            if (flightFromToDate != null) {
                listSearch.add(flightFromToDate);
            }
        }
        searchResult.setListSearch(listSearch);
        return searchResult;
    }



}
