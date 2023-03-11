package app.controllers;

import app.entities.Destination;
import app.services.DestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destination")
public class DestinationController {
    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping
    public List<Destination> getAll() {
        return destinationService.findAll();
    }

    @PostMapping
    public ResponseEntity<Destination> create(@RequestBody Destination destination) {
        destination = new Destination("Moscow", "Russia", "Eurasia", 12L);
        destinationService.save(destination);
        destination = new Destination("Ural", "Russia", "Eurasia", 12L);
        destinationService.save(destination);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(destination);
    }

    @GetMapping("/{id}")
    public Destination findById(@PathVariable Long id) {
        return destinationService.findById(id);
    }

}
