package io.codelex.flightplanner.Repositories;

import io.codelex.flightplanner.Flight.Flight;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FlightRepository {

    private final List<Flight> savedFlights = new ArrayList<>();

    public void saveFlight(Flight flight) {
        savedFlights.add(flight);
    }

    public Optional<Flight> getFlight(Long id) {
        return savedFlights.stream().filter(flight -> flight.getId().equals(id)).findFirst();
    }

    public List<Flight> getSavedFlights() {
        return savedFlights;
    }

    public void deleteFlight(Flight flight) {
        savedFlights.remove(flight);
    }

    public void clearFlights() {
        savedFlights.clear();
    }
}

