package io.codelex.flightplanner.Services;

import io.codelex.flightplanner.Flight.Flight;
import io.codelex.flightplanner.Flight.FlightRequest;
import io.codelex.flightplanner.Repositories.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class FlightService {
    private final AtomicLong counter = new AtomicLong();

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Optional<Flight> getFlight(Long id) {
        return flightRepository.getFlight(id);
    }

    public Flight createFlight(Flight flight) {

        return new Flight(
                counter.incrementAndGet(),
                flight.getFrom(),
                flight.getTo(),
                flight.getCarrier(),
                flight.getDepartureTime(),
                flight.getArrivalTime()
        );
    }

    public boolean isFlightValid(Flight flight) {

        if (flight.getFrom().getCountry().replaceAll("\\s+", "")
                .equalsIgnoreCase(flight.getTo().getCountry().replaceAll("\\s+", "")) &&
                flight.getFrom().getCity().replaceAll("\\s+", "")
                        .equalsIgnoreCase(flight.getTo().getCity().replaceAll("\\s+", "")) &&
                flight.getFrom().getAirport().replaceAll("\\s+", "")
                        .equalsIgnoreCase(flight.getTo().getAirport().replaceAll("\\s+", ""))) {
            return true;
        }
        return flight.getFrom().equals(flight.getTo()) ||
                flight.getDepartureTime().compareTo(flight.getArrivalTime()) >= 0;
    }

    public boolean isFlightUnique(Flight flight) {
        return flightRepository.getSavedFlights().stream().noneMatch(existingFlight ->
                existingFlight.getFrom().equals(flight.getFrom()) &&
                        existingFlight.getTo().equals(flight.getTo()) &&
                        existingFlight.getCarrier().equals(flight.getCarrier()) &&
                        existingFlight.getDepartureTime().equals(flight.getDepartureTime()) &&
                        existingFlight.getArrivalTime().equals(flight.getArrivalTime())
        );
    }

    public boolean isFlightRequestValid(FlightRequest flight) {
        return !flight.getFrom().equals(flight.getTo());
    }
}



