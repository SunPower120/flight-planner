package io.codelex.flightplanner.Controllers;

import io.codelex.flightplanner.Exceptions.InvalidFlightRequestException;
import io.codelex.flightplanner.Flight.Airport;
import io.codelex.flightplanner.Flight.Flight;
import io.codelex.flightplanner.Flight.FlightRequest;
import io.codelex.flightplanner.Services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


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
    public ResponseEntity<?> searchFlights(@Valid @RequestBody FlightRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>("Invalid Flight Request", HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(customerService.searchFlights(request), HttpStatus.OK);
        } catch (InvalidFlightRequestException ex) {
            return new ResponseEntity<>("Invalid Flight Request", HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>("No Matching Flights", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/flights/{id}")
    public Flight fetchFlight(@PathVariable Long id) {
        return customerService.fetchFlight(id);
    }
}
