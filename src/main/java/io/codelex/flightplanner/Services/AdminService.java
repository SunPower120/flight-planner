package io.codelex.flightplanner.Services;

import io.codelex.flightplanner.Flight.Flight;
import io.codelex.flightplanner.Repositories.FlightRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final FlightService flightService;
    private final FlightRepository flightRepository;

    public AdminService(FlightService flightService, FlightRepository flightRepository) {
        this.flightService = flightService;
        this.flightRepository = flightRepository;
    }

    public Flight createFlight(Flight flight) {
        if (flightService.isFlightValid(flight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!flightService.isFlightUnique(flight)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Flight newFlight = flightService.createFlight(flight);
        flightRepository.saveFlight(newFlight);
        return newFlight;

    }

    public List<Flight> getFlights() {
        return flightRepository.getSavedFlights();
    }

    public void deleteFlight(Long id) {
        Optional<Flight> flight = flightRepository.getSavedFlights().stream()
                .filter(f -> id.equals(f.getId()))
                .findFirst();

        flight.ifPresentOrElse(flightRepository::deleteFlight,
                () -> {
                    throw new ResponseStatusException(HttpStatus.OK);
                });
    }

    public Flight fetchFlight(Long id) {
        return flightRepository.getFlight(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}