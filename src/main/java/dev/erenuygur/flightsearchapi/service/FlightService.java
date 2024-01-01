package dev.erenuygur.flightsearchapi.service;

import dev.erenuygur.flightsearchapi.model.dto.FlightResponseDTO;
import dev.erenuygur.flightsearchapi.model.entity.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    List<FlightResponseDTO> getAllFlights();

    List<FlightResponseDTO> searchFlights(String departureCity, String arrivalCity, LocalDateTime departureDateTime, LocalDateTime returnDateTime);

    Flight createFlight(Flight flight);

    Flight updateFlight(Long id, Flight flight);

    void deleteFlight(Long id);
}
