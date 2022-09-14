package app.services;

import app.entities.Destination;
import app.entities.Route;
import app.entities.Search;
import app.repositories.SearchRepository;
import app.util.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class SearchServiceImpl with CRUD methods for Search
 *
 * @author Eugene Kolyshev
 */
@Service
public class SearchServiceImpl implements SearchService {

    private final SearchRepository searchRepository;
    private final DestinationService destinationService;

    @Autowired
    public SearchServiceImpl(SearchRepository searchRepository, Route route, DestinationService destinationService) {
        this.searchRepository = searchRepository;
        this.destinationService = destinationService;
    }


    @Override
    public List<Search> getAll() {
        return searchRepository.findAll();
    }

    @Override
    public Search getById(Long id) {
        return searchRepository.findById(id).isPresent()
                ? searchRepository.findById(id).get()
                : null;
    }

    @Override
    public void save(Search search) {
        searchRepository.save(search);
        //System.out.println("search id=" + search.getId() + " was created");
    }

    @Override
    public void update(Search search) {
        searchRepository.save(search);
    }

    @Override
    public void removeById(Long id) {
        searchRepository.deleteById(id);
    }

    @Override
    public Search get(String from, String to, String when) {

        Search search = new Search();
        Destination destinationFrom;
        Destination destinationTo;
        LocalDate departureDate;
        if (from != null && to != null && when != null) {
            try {
                destinationFrom = destinationService.getDestinationByCity(from).get();
                destinationTo = destinationService.getDestinationByCity(to).get();
            } catch (Exception e){
                System.out.println("Destination ERROR");
                destinationFrom = null;
                destinationTo = null;
            }
            try {
                departureDate = LocalDate.parse(when);
            } catch (Exception e) {
                System.out.println("LocalDate ERROR");
                departureDate = null;
            }
        } else {
            destinationFrom = null;
            destinationTo = null;
            departureDate = null;
        }

        if (destinationFrom != null && destinationTo != null & departureDate != null) {
            Route route = new Route();
            route.setFrom(destinationFrom);
            route.setTo(destinationTo);
            route.setDepartureDate(departureDate);
            route.setCategory(Category.ECONOMY);
            List<Route> listRouts = new ArrayList<>();
            listRouts.add(route);
            search.setRoutes(listRouts);
        } else {
            search = null;
        }
        return search;
    }
}
