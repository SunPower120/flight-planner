package io.codelex.flightplanner.Services;

import io.codelex.flightplanner.Dto.PageResult;
import io.codelex.flightplanner.Exceptions.InvalidFlightRequestException;
import io.codelex.flightplanner.Exceptions.NoMatchingFlights;
import io.codelex.flightplanner.Flight.Airport;
import io.codelex.flightplanner.Flight.Flight;
import io.codelex.flightplanner.Flight.FlightRequest;
import io.codelex.flightplanner.Repositories.FlightRepository;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final FlightService flightService;
    private final FlightRepository flightRepository;

    public CustomerService(FlightService flightService, FlightRepository flightRepository) {
        this.flightService = flightService;
        this.flightRepository = flightRepository;
    }

    public List<Airport> searchAirports(@RequestParam("search") String search) {

        return flightRepository.getSavedFlights().stream()
                .map(Flight::getFrom)
                .filter(from -> from.getAirport().toLowerCase().contains(search.trim().toLowerCase())
                        || from.getCountry().toLowerCase().contains(search.trim().toLowerCase())
                        || from.getCity().toLowerCase().contains(search.trim().toLowerCase()))
                .toList();
    }

    public PageResult<FlightRequest> searchFlights(@Valid @RequestBody FlightRequest request) {
        
        if (!flightService.isFlightRequestValid(request)) {
            throw new InvalidFlightRequestException();
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

        return pageResult;
    }

    public Flight fetchFlight(@PathVariable Long id) {

        Optional<Flight> flightOptional = flightRepository.getFlight(id);

        if (flightOptional.isPresent()) {
            return flightOptional.get();
        } else {
            throw new NoMatchingFlights();
        }
    }
}
