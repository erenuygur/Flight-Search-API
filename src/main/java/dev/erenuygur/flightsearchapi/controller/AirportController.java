package dev.erenuygur.flightsearchapi.controller;

import dev.erenuygur.flightsearchapi.model.entity.Airport;
import dev.erenuygur.flightsearchapi.service.AirportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/airports")
public class AirportController {

    private final AirportService airportService;

    @Operation(summary = "Get all airport information from the database.", operationId = "getAllAirports")
    @GetMapping()
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    @Operation(summary = "Search for airports by city.", operationId = "getAirportsByCity")
    @GetMapping("/cities/{city}")
    public ResponseEntity<List<Airport>> getAirportsByCity(@PathVariable String city) {
        List<Airport> airports = airportService.getAirportsByCity(city);

        return ResponseEntity.ok(airports);
    }

    @Operation(summary = "Search for airports by id.", operationId = "getAirportByAirportId")
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportByAirportId(@PathVariable Long id) {
        Airport airport = airportService.getAirportById(id);

        return ResponseEntity.ok(airport);
    }

    @Operation(summary = "Add new airport to database.", operationId = "createAirport")
    @PostMapping
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        Airport createdAirport = airportService.createAirport(airport);
        return ResponseEntity.ok().body(createdAirport);
    }

    @Operation(summary = "Update an existing airport from database.", operationId = "updateAirport")
    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        Airport updatedAirport = airportService.updateAirport(id, airport);
        if (updatedAirport != null) {
            return ResponseEntity.ok(updatedAirport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete an existing airport from the database.", operationId = "deleteAirport")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }
}
