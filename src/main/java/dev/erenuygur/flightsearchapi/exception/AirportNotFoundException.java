package dev.erenuygur.flightsearchapi.exception;

public class AirportNotFoundException extends RuntimeException {
    public AirportNotFoundException() {
        super("Airport is not exists.");
    }
}
