package app.services;

import app.entities.Route;
import app.entities.Search;

import java.util.List;

public interface SearchService {

    List<Search> getAll();

    Search getById(Long id);

    void save(Search search);

    void update(Search search);

    void removeById(Long id);

    Search get(String from, String to, String when);
}
