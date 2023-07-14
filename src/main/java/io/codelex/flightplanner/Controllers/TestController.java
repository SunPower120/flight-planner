package io.codelex.flightplanner.Controllers;


import io.codelex.flightplanner.Repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api/clear")
public class TestController {

    private final FlightRepository flightRepository;

    public TestController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @PostMapping
    public ResponseEntity<Void> clear() {
        flightRepository.clearFlights();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
