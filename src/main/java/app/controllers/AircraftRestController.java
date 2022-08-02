package app.controllers;

import app.entities.Aircraft;
import app.services.AircraftService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controllers for Aircraft entity
 *
 * @author Eugene Kolyshev
 */
@Api(value = "Aircraft Rest Controller", tags = "aircraft")
@RestController
@RequestMapping("/api/aircraft")
public class AircraftRestController {
    /**
     * Field aircraftService with CRUD service for Aircraft Entity
     *
     * @see app.services.AircraftServiceImpl
     * @see Aircraft
     */
    private final AircraftService aircraftService;

    @Autowired
    public AircraftRestController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    /**
     * Method getAll() to get all Aircraft from DB
     *
     * @return List of Aircraft
     */
    @ApiOperation(value = "Get List of all Aircraft")
    @GetMapping()
    private ResponseEntity<List<Aircraft>> getAll() {
        final List<Aircraft> aircraft = aircraftService.getAll();
        return aircraft != null && !aircraft.isEmpty()
                ? new ResponseEntity<>(aircraft, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method getOne(Long) to get an Aircraft by id
     *
     * @param id aircraft_id
     * @return Aircraft
     */
    @ApiOperation(value = "Get one Aircraft")
    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        final Aircraft aircraft = aircraftService.getById(id);
        return aircraft != null
                ? new ResponseEntity<>(aircraft, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method create() to create a new Aircraft
     *
     * @param aircraft
     */
    @ApiOperation(value = "Create Aircraft")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Aircraft aircraft) {
        aircraftService.save(aircraft);
        return new ResponseEntity<>(aircraft, HttpStatus.CREATED);
    }

    /**
     * Method update() to update an Aircraft
     *
     * @param aircraft
     */
    @ApiOperation(value = "Update Aircraft")
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Aircraft aircraft) {
        aircraftService.update(aircraft);
        return new ResponseEntity<>(aircraft, HttpStatus.OK);
    }

    /**
     * Method delete() to delete an Aircraft by id
     *
     * @param id
     */
    @ApiOperation(value = "Delete Aircraft")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Aircraft deletedAircraft = aircraftService.getById(id);
        aircraftService.removeById(id);
        return new ResponseEntity<>(deletedAircraft, HttpStatus.OK);
    }

}
