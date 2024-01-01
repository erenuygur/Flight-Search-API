package dev.erenuygur.flightsearchapi.controller;

import dev.erenuygur.flightsearchapi.model.entity.Flight;
import dev.erenuygur.flightsearchapi.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/secure/flights")
public class SecureFlightController {

    private final FlightService flightService;

    @Operation(summary = "Add new flight to database.", operationId = "createFlight")
    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight createdFlight = flightService.createFlight(flight);
        return ResponseEntity.ok().body(createdFlight);
    }

    @Operation(summary = "Update an existing flight from database.", operationId = "updateFlight")
    @PutMapping("/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long flightId, @RequestBody Flight flight) {
        Flight updatedFlight = flightService.updateFlight(flightId, flight);
        if (updatedFlight != null) {
            return ResponseEntity.ok(updatedFlight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete an existing flight from database.", operationId = "deleteFlight")
    @DeleteMapping("/{flightId}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long flightId) {
        flightService.deleteFlight(flightId);
        return ResponseEntity.noContent().build();
    }

}
