package dev.erenuygur.flightsearchapi.controller;

import dev.erenuygur.flightsearchapi.model.entity.Airport;
import dev.erenuygur.flightsearchapi.service.AirportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/secure/airports")
public class SecureAirportController {
    private final AirportService airportService;

    @Operation(summary = "Add new airport to database.", operationId = "createAirport")
    @PostMapping
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        Airport createdAirport = airportService.createAirport(airport);
        return ResponseEntity.ok().body(createdAirport);
    }

    @Operation(summary = "Update an existing airport from database.", operationId = "updateAirport")
    @PutMapping("/{airportId}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long airportId, @RequestBody Airport airport) {
        Airport updatedAirport = airportService.updateAirport(airportId, airport);
        if (updatedAirport != null) {
            return ResponseEntity.ok(updatedAirport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete an existing airport from the database.", operationId = "deleteAirport")
    @DeleteMapping("/{airportId}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long airportId) {
        if (airportId == null || airportId <= 0) {
            return ResponseEntity.badRequest().build();
        }

        airportService.deleteAirport(airportId);
        return ResponseEntity.noContent().build();
    }
}
