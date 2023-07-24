package io.codelex.flightplanner.Exceptions;

public class InvalidFlightRequestException extends RuntimeException {
    public InvalidFlightRequestException() {
        super("Invalid Flight Request");
    }
}
