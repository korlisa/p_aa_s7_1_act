package app.controllers;

import app.entities.Destination;
import app.services.DestinationService;
import app.services.DestinationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST controllers for Destination
 * @author Andrey Mitukov
 */

@Api(tags = "destination rest controller")
@RestController
@RequestMapping("/api/destination")
public class DestinationRestController {

    private final DestinationService destinationService;

    @Autowired
    public DestinationRestController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    /**
     * Method getALL() to get all Destinations
     *
     * @see DestinationServiceImpl#getAllDestinations()
     */
    @ApiOperation(value = "get all Destinations")
    @GetMapping
    public ResponseEntity<List<Destination>>findAllDestinations(){
        return new ResponseEntity<>(destinationService.getAllDestinations(), HttpStatus.OK);
    }


    /**
     * Method update() to add Destination
     *
     * @see DestinationServiceImpl#saveDestination(Destination)
     */

    @ApiOperation(value = "create new Destination")
    @PostMapping
    public ResponseEntity<Destination> addNewDestination (@RequestBody Destination destination) {
        return new ResponseEntity<>(destinationService.saveDestination(destination), HttpStatus.CREATED);
    }
    
    /**
     * Method update() to update Destination
     *
     * @see DestinationServiceImpl#updateDestination(Destination)
     */
    @ApiOperation(value = "update Destination")
    @PatchMapping
    public ResponseEntity<Destination> updateDestination (@RequestBody Destination destination) {
        destinationService.updateDestination(destination);
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }
    
    /**
     * Method get() to get Destination by IdAndCityAndCountry_nameAndContinent
     *
     * @see DestinationServiceImpl#getDestinationById(Long)
     */
    @ApiOperation(value = "get unique Destination by Id")
    @GetMapping("/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable(value ="id") Long id) {
        return new ResponseEntity(destinationService.getDestinationById(id), HttpStatus.OK);
    }

    /**
     * Method get() to get Destination by IdAndCityAndCountry_nameAndContinent
     *
     * @see DestinationServiceImpl#getDestinationByCountryName(String)
     */
    @ApiOperation(value = "get unique Destination by country")
    @GetMapping("/country")
    public ResponseEntity<Destination> getDestinationByCountry(@RequestParam(value = "country_name", required = false)  String country_name) {
        return new ResponseEntity(destinationService.getDestinationByCountryName(country_name), HttpStatus.OK);
    }

    /**
     * Method get() to get Destination by IdAndCityAndCountry_nameAndContinent
     *
     * @see DestinationServiceImpl#getDestinationByCity(String)
     */
    @ApiOperation(value = "get unique Destination by city")
    @GetMapping("/city")
    public ResponseEntity<Destination> getDestinationByCity(@RequestParam(value = "city", required = false) String city) {
        return new ResponseEntity(destinationService.getDestinationByCity(city), HttpStatus.OK);
    }

}
