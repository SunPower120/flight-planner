package io.codelex.flightplanner.Controllers;

import io.codelex.flightplanner.Dto.PageResult;
import io.codelex.flightplanner.Flight.Airport;

import io.codelex.flightplanner.Flight.Flight;
import io.codelex.flightplanner.Flight.FlightRequest;
import io.codelex.flightplanner.Repositories.FlightRepository;
import io.codelex.flightplanner.Services.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class CustomerController {

    private final FlightService flightService;
    private final FlightRepository flightRepository;

    public CustomerController(FlightService flightService, FlightRepository flightRepository) {
        this.flightService = flightService;
        this.flightRepository = flightRepository;
    }

    @GetMapping("/airports")
    public List<Airport> searchAirports(@RequestParam("search") String search) {

        return flightRepository.getSavedFlights().stream()
                .map(Flight::getFrom)
                .filter(from -> from.getAirport().toLowerCase().contains(search.trim().toLowerCase())
                        || from.getCountry().toLowerCase().contains(search.trim().toLowerCase())
                        || from.getCity().toLowerCase().contains(search.trim().toLowerCase()))
                .toList();
    }

    @PostMapping("/flights/search")
    public ResponseEntity<PageResult<FlightRequest>> searchFlights(@RequestBody FlightRequest request) {
        if (!flightService.isFlightRequestValid(request)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Flight> flights = flightRepository.getSavedFlights().stream()
                .filter(flight -> flight.getFrom().getAirport().equals(request.getFrom()))
                .filter(flight -> flight.getTo().getAirport().equals(request.getTo()))
                .filter(flight -> flight.getDepartureTime().startsWith(request.getDepartureDate()))
                .collect(Collectors.toList());

        PageResult<FlightRequest> pageResult = new PageResult<>();
        pageResult.setItems(flights);
        pageResult.setTotalItems(flights.size());
        pageResult.setPage(0);

        return ResponseEntity.ok(pageResult);
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
