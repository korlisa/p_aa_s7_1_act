package app.controllers;

import app.entities.*;
import app.services.DestinationService;
import app.services.SearchResultService;
import app.services.SearchService;
import app.util.SearchResultConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * REST controllers for SearchResult
 *
 * @author Eugene Kolyshev
 * @see SearchResult
 */
@Api(value = "SearchResult Rest Controller", tags = "searchResult")
@RestController
@RequestMapping("api/search")
public class SearchResultRestController {

    private final SearchResultService searchResultService;
    private final SearchService searchService;
    private final DestinationService destinationService;

    @Autowired
    public SearchResultRestController(SearchResultService searchResultService, SearchService searchService, DestinationService destinationService) {
        this.searchResultService = searchResultService;
        this.searchService = searchService;
        this.destinationService = destinationService;
    }


    @ApiOperation(value = "Get searchResult")
    @GetMapping(value = "/{from}/{to}/{when}")
    @ResponseBody
    private ResponseEntity<?> getSearchResult(@PathVariable("from") String from,
                                              @PathVariable("to") String to, @PathVariable("when") String when) {

        Search search = searchService.get(from, to, when);
        SearchResult searchResult = null;
        if (search != null) {
            searchService.save(search);
            searchResult = searchResultService.getSearchResult(search);
        }

        return searchResult != null
                ? new ResponseEntity<>(new SearchResultConverter().convert(searchResult), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

   /* @ApiOperation(value = "Get searchResult")
    @PostMapping(value = "/result")
    @ResponseBody
    private ResponseEntity<?> getSearchResult(@RequestBody String jsonRoute) throws ParseException {
        Search search = null;
        //search = jsonParser.getSearchByJson(jsonRoute);
        SearchResult searchResult = null;

        if (search != null) {
            searchService.save(search);
            searchResult = searchResultService.getSearchResult(search);
        }
        return searchResult != null
                ? new ResponseEntity<>(searchResult, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

}
