/*
package app.controllers;


import app.entities.Search;
import app.services.SearchService;
import app.util.JsonParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

*/
/**
 * REST controllers for Search entity
 *
 * @author Eugene Kolyshev
 * @see Search
 *//*


@Api(value = "Search Rest Controller", tags = "search")
@RestController
@RequestMapping("api/search")
public class SearchRestController {

    private final SearchService searchService;
    private final JsonParser jsonParser;

    @Autowired
    public SearchRestController(SearchService searchService, JsonParser jsonParser) {
        this.searchService = searchService;
        this.jsonParser = jsonParser;
    }

    */
/**
     * Method getSearchResult() to get all SearchResult
     *
     * @return List of Search, OK
     *//*


    @ApiOperation(value = "Get all Search")
    @GetMapping()
    private ResponseEntity<List<Search>> getAllSearch() {
        List<Search> search = searchService.getAll();
        return search != null && !search.isEmpty()
                ? new ResponseEntity(search, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    */
/**
     * Method createSearch() to create new Search
     *
     * @return search, OK
     *//*

    @ApiOperation(value = "Create Search")
    @PostMapping()
    private ResponseEntity<?> createSearch(@RequestBody String jsonRoute) throws org.json.simple.parser.ParseException {
        Search search = jsonParser.getSearchByJson(jsonRoute);
        searchService.save(search);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    */
/**
     * Method getSearch() to get Search by id
     *
     * @return search, OK
     *//*

    @ApiOperation(value = "Get Search by id")
    @GetMapping("{id}")
    private ResponseEntity<?> getSearch(@PathVariable("id") Long id) {
        final Search search = searchService.getById(id);
        return search != null
                ? new ResponseEntity<>(search, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    */
/**
     * Method updateSearch() to update Search
     *
     * @return search, OK
     *//*

    @ApiOperation(value = "Update Search")
    @PutMapping()
    private ResponseEntity<?> updateSearch(@RequestBody Search search) {
        //searchService.save(search);
        return new ResponseEntity<>(search, HttpStatus.OK);
    }


}
*/
