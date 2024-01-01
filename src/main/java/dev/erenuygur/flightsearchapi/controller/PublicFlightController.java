package dev.erenuygur.flightsearchapi.controller;

import dev.erenuygur.flightsearchapi.model.dto.FlightResponseDTO;
import dev.erenuygur.flightsearchapi.model.dto.FlightRequestDTO;
import dev.erenuygur.flightsearchapi.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public/flights")
public class PublicFlightController {

    private final FlightService flightService;


    @Operation(summary = "Get all flight information from the database.", operationId = "getAllFlights")
    @GetMapping
    public ResponseEntity<List<FlightResponseDTO>> getAllFlights() {
        List<FlightResponseDTO> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @Operation(
            summary = "Search for flights by departure and arrival details.",
            description = "Optionally, if the return date-time is given, it will also provide flight information for the return.",
            operationId = "searchFlights"
    )
    @PostMapping("/search")
    public ResponseEntity<List<FlightResponseDTO>> searchFlights(@RequestBody FlightRequestDTO searchRequest) {
        List<FlightResponseDTO> flights = flightService.searchFlights(
                searchRequest.getDepartureCity(),
                searchRequest.getArrivalCity(),
                searchRequest.getDepartureDateTime(),
                searchRequest.getReturnDateTime()
        );
        return ResponseEntity.ok(flights);
    }
}
