package io.codelex.flightplanner.Services;

import io.codelex.flightplanner.Repositories.FlightRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TestService {

    private final FlightRepository flightRepository;

    public TestService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void clear() {
        flightRepository.clearFlights();
        throw new ResponseStatusException(HttpStatus.OK);
    }
}
