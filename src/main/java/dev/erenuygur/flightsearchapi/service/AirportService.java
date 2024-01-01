package dev.erenuygur.flightsearchapi.service;

import dev.erenuygur.flightsearchapi.model.entity.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportService {
    List<Airport> getAllAirports();

    Optional<Airport> getAirportById(Long id);

    List<Airport> getAirportsByCity(String city);

    Airport createAirport(Airport airport);

    Airport updateAirport(Long id, Airport airport);

    void deleteAirport(Long id);
}
