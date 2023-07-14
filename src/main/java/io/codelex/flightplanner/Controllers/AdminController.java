package io.codelex.flightplanner.Controllers;

import io.codelex.flightplanner.Flight.Flight;
import io.codelex.flightplanner.Repositories.FlightRepository;
import io.codelex.flightplanner.Services.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin-api")
public class AdminController {

    private final FlightService flightService;
    private final FlightRepository flightRepository;

    public AdminController(FlightService flightService, FlightRepository flightRepository) {
        this.flightService = flightService;
        this.flightRepository = flightRepository;
    }

    @PutMapping("/flights")
    public ResponseEntity<?> createFlight(@Valid @RequestBody Flight flight) {
        try {
            if (flightService.isFlightValid(flight)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!flightService.isFlightUnique(flight)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            Flight newFlight = flightService.createFlight(flight);
            flightRepository.saveFlight(newFlight);
            return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getFlights() {

        List<Flight> flights = flightRepository.getSavedFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @DeleteMapping("/flights/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        Optional<Flight> flight = flightRepository.getSavedFlights().stream()
                .filter(f -> id.equals(f.getId()))
                .findFirst();

        flight.ifPresent(flightRepository::deleteFlight);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<?> fetchFlight(@PathVariable Long id) {
        Optional<Flight> flightOptional = flightRepository.getFlight(id);

        if (flightOptional.isPresent()) {
            return new ResponseEntity<>(flightOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
