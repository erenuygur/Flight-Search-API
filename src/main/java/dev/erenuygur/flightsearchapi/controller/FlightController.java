package dev.erenuygur.flightsearchapi.controller;

import dev.erenuygur.flightsearchapi.model.dto.FlightResponseDTO;
import dev.erenuygur.flightsearchapi.model.dto.FlightRequestDTO;
import dev.erenuygur.flightsearchapi.model.entity.Flight;
import dev.erenuygur.flightsearchapi.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

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
    @GetMapping("/search")
    public ResponseEntity<List<FlightResponseDTO>> searchFlights(@RequestBody FlightRequestDTO searchRequest) {
        List<FlightResponseDTO> flights = flightService.searchFlights(
                searchRequest.getDepartureCity(),
                searchRequest.getArrivalCity(),
                searchRequest.getDepartureDateTime(),
                searchRequest.getReturnDateTime()
        );
        return ResponseEntity.ok(flights);
    }

    @Operation(summary = "Add new flight to database.", operationId = "createFlight")
    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight createdFlight = flightService.createFlight(flight);
        return ResponseEntity.ok().body(createdFlight);
    }

    @Operation(summary = "Update an existing flight from database.", operationId = "updateFlight")
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        Flight updatedFlight = flightService.updateFlight(id, flight);
        if (updatedFlight != null) {
            return ResponseEntity.ok(updatedFlight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete an existing flight from database.", operationId = "deleteFlight")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}
