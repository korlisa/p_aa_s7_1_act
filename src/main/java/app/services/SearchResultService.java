package app.services;

import app.entities.Destination;
import app.entities.Flight;
import app.entities.Search;
import app.entities.SearchResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface SearchResultService{

    SearchResult getSearchResult(Search search);


}
