
package app.controllers;


import app.entities.Search;
import app.services.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * REST controllers for Search entity
 * @author Komarov Rostislav
 * @see Search
 */


@Api(value = "Search Rest Controller", tags = "search")
@RestController
@RequestMapping("/api/search")
public class SearchRestController {

    private final SearchService searchService;

    @Autowired
    public SearchRestController(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * <p><em>getAllSearch</em> self explained - getting all entities of {@link app.entities.Search} in list</p>
     * @return returns 200 status and list of every search
     * or 204 No Content status if nothing found
     */

    @ApiOperation(value = "Get every search")
    @GetMapping
    public ResponseEntity<List<Search>> getAllSearch() {
        List<Search> search = searchService.getAllSearches();
        if(search.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(search);
        }
    }

    /**
     * <p><em>createSearch</em> creating {@link app.entities.Search} class from request body and putting into database</p>
     * @param search {@link app.entities.Search} class from request body
     * @return returns 200 status and object itself in response body if added successfully
     * or 400 bad request status and object in response body if something goes wrong
     */

    @ApiOperation(value = "Create Search")
    @PostMapping
    public ResponseEntity<Search> createSearch(@RequestBody Search search) {
        try {
            searchService.createSearch(search);
            return ResponseEntity.ok(search);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(search);
        }
    }

    /**
     * <p><em>getSearch</em> getting desired {@link app.entities.Search} element by id</p>
     * @param id Id of desired element
     * @return 200 status and object if found
     * or 404 not found if object isn't exist
     */

    @ApiOperation(value = "Get search by id")
    @GetMapping("{id}")
    public ResponseEntity<Search> getSearch(@PathVariable("id") Long id) {
        Optional<Search> target = searchService.getSearch(id);
        if(target.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(target.get());
        }
    }

    /**
     * <p><em>updateSearch</em> updating object in database with same primary key(id)</p>
     * @param search updated {@link app.entities.Search} class from request body
     * @return status 200 OK and updated object itself
     * or 400 bad request and object if something goes wrong
     */

    @ApiOperation(value = "Update search")
    @PutMapping
    public ResponseEntity<Search> updateSearch(@RequestBody Search search) {
        try {
            searchService.updateSearch(search);
            return ResponseEntity.ok(search);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(search);
        }
    }

    /**
     * <p><em>deleteSearch</em> deleting desired element from database</p>
     * @param id Id of desired element
     * @return status 200 OK and deleted object itself
     * or 404 Not Found if desired object isn't exist
     */

    @ApiOperation(value = "Delete search")
    @DeleteMapping("{id}")
    public ResponseEntity<Search> deleteSearch(@PathVariable Long id) {
        try {
            Optional<Search> target = searchService.getSearch(id);
            searchService.deleteSearch(id);
            return ResponseEntity.ok(target.get());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


}

