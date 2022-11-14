package app.services;

import app.entities.Category;
import app.entities.Seat;
import java.util.List;
import java.util.Optional;

/**
 * The service is allowing all operations with {@link app.entities.Seat} including
 * CRUD, find by {@link app.entities.Flight} and {@link app.entities.Category}, get count of
 * sold, unsold and registered seats.
 *
 * @author Shchepin Maksim
 */
public interface SeatService {

    /**
     * Find a {@link app.entities.Seat} among all previously saved seats from all registered flights
     * by their unique <code>id</code> and return <code>Optional</code> result.
     *
     * @param id seat id to be found.
     * @return an {@link java.util.Optional} with the find results.
     */
    Optional<Seat> findById(Long id);

    /**
     * Find all seats among all previously saved seats from all registered flights
     * and return <code>List</code> of find results.
     *
     * @return a {@link java.util.List} with the find results.
     */
    List<Seat> findAll();

    /**
     * Save a {@link app.entities.Seat} into repository. Use the returned instance for further operations as the save operation
     * at least will change the seat id.
     *
     * @param seat to be saved.
     * @return a consistent state {@link app.entities.Seat}.
     */
    Seat save(Seat seat);

    /**
     * Update an existing {@link app.entities.Seat}. Use the returned instance for further operations.
     *
     * @param seat to be updated.
     * @return the saved seat.
     */
    Seat update(Seat seat);

    /**
     * Delete an existing {@link app.entities.Seat}: at first finds for a seat by <code>id</code> and if successful deletes it.
     * Returns <code>true</code> if a seat found and <code>false</code> otherwise.
     *
     * @param id seat id to be deleted.
     * @return the updated seat.
     */
    boolean deleteById(Long id);

    /**
     * Clear seats' repository.
     *
     */
    void deleteAll();

    /**
     * Find for all seats of a {@link app.entities.Flight} with the specified <code>id</code>.
     *
     * @param id flight id to be used for the search.
     * @return the list of seats for the specified flight.
     */
    List<Seat> findAllByFlightId(Long id);

    /**
     * Find for all seats of a {@link app.entities.Flight} with the specified <code>id</code>
     * and {@link app.entities.Category}.
     *
     * @param id the flight id to be used for the search.
     * @param category a type of seat to be used for the search.
     * @return the list of seats for the specified flight and category.
     */
    List<Seat> findAllByFlightIdAndCategory(Long id, Category category);

    /**
     * Return the number of sold seats for the specified flight by <code>id</code>.
     *
     * @param id the flight id to be used for the search.
     * @return the number of sold seats for the specified {@link app.entities.Flight}.
     */
    int getSoldByFlightId(Long id);

    /**
     * Return the number of unsold seats for the specified flight by <code>id</code>.
     *
     * @param id the flight id to be used for the search.
     * @return the number of unsold seats for the specified {@link app.entities.Flight}.
     */
    int getUnsoldByFlightId(Long id);

    /**
     * Return the number of registered seats for the specified flight by <code>id</code>.
     *
     * @param id the flight id to be used for the search.
     * @return the number of registered seats for the specified {@link app.entities.Flight}.
     */
    int getRegisteredByFlightId(Long id);
}
