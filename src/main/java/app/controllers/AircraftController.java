package app.controllers;

import app.entities.Aircraft;
import app.services.AircraftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public List<Aircraft> getList() {
        return aircraftService.findAll();
    }


    @PostMapping
    public ResponseEntity<Aircraft> create(@RequestBody Aircraft aircraft) {
        aircraft = new Aircraft("s1", "boeing", "poxuy", 2010, 500);
        aircraftService.save(aircraft);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(aircraft);
    }

    @GetMapping("/{id}")
    public Aircraft getById(@PathVariable Long id) {
        Aircraft aircraft = aircraftService.findById(id);
        return aircraft;
    }

//    @GetMapping("/{boardNumber}")
//    public Aircraft getByBoardBumber(@PathVariable String boardNumber) {
//        return aircraftServiceTest.findByBoardNumber(boardNumber);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        aircraftService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("The user has been successfully deleted");
    }
}
