package io.codelex.flightplanner.Exceptions;

public class NoMatchingFlights extends RuntimeException {
    public NoMatchingFlights() {
        super("No Matching Flights");
    }
}

