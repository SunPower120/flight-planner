package io.codelex.flightplanner.Controllers;

import io.codelex.flightplanner.Flight.Airport;
import io.codelex.flightplanner.Flight.Flight;
import io.codelex.flightplanner.Flight.FlightRequest;
import io.codelex.flightplanner.Services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @GetMapping("/airports")
    public List<Airport> searchAirports(@RequestParam("search") String search) {
        return customerService.searchAirports(search);
    }

    @PostMapping("/flights/search")
    public Object searchFlights(@Valid @RequestBody FlightRequest request) {
        return customerService.searchFlights(request);
    }

    @GetMapping("/flights/{id}")
    public Flight fetchFlight(@PathVariable Long id) {
        return customerService.fetchFlight(id);
    }
}
