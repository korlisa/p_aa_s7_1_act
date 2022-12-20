package app.controllers;

import app.entities.Destination;
import app.services.DestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controllers for Destination
 * @author Andrey Mitukov
 * @author Olga Maslova
 */

@RestController
@RequestMapping("/api/destination")
public class DestinationRestController {

    private final DestinationService destinationService;

    public DestinationRestController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

  @GetMapping
 public ResponseEntity<List<Destination>> getAllDestinations() {
        return new ResponseEntity<>(destinationService.getAllDestinations(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Destination> createDestination(@RequestBody Destination destination) {
        return new ResponseEntity<>(destinationService.saveDestination(destination), HttpStatus.CREATED);
  }

 @PutMapping("/{id}")
  public ResponseEntity<Destination> updateDestination(@RequestBody Destination destination) {
     if(destination == null) {
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     }
     destinationService.updateDestination(destination);
     return new ResponseEntity<>(destination, HttpStatus.OK);
 }

 @GetMapping("/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable("id") Long id) {
     Destination destination = destinationService.getDestinationById(id);
     if(destination == null) {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
     return new ResponseEntity<>(destination, HttpStatus.OK);
 }

 @GetMapping("/city")
    public ResponseEntity<Destination> getDestinationByCity(@RequestParam (value = "city", required = false) String city) {
        return new ResponseEntity<>(destinationService.getDestinationByCity(city), HttpStatus.OK);
 }
 @GetMapping("/country")
    public ResponseEntity<Destination> getDestinationByCountry(@RequestParam(value = "country_name", required = false) String country_name) {
        return new ResponseEntity<>(destinationService.getDestinationByCountry_name(country_name), HttpStatus.OK);
 }

    @DeleteMapping
    public void deleteAllDestination () {
        destinationService.deleteAllDestination();
    }

 }











