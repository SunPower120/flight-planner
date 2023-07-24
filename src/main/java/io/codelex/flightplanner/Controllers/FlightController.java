package io.codelex.flightplanner.Controllers;

import io.codelex.flightplanner.Flight.Flight;
import io.codelex.flightplanner.Services.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/{id}")
    public Optional<Flight> getFlight(@PathVariable Long id) {
        return flightService.getFlight(id);

    }


}