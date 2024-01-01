package dev.erenuygur.flightsearchapi.controller;

import dev.erenuygur.flightsearchapi.model.entity.Airport;
import dev.erenuygur.flightsearchapi.service.AirportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public/airports")
public class PublicAirportController {

    private final AirportService airportService;

    @Operation(summary = "Get all airport information from the database.", operationId = "getAllAirports")
    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    @Operation(summary = "Search for airports by city.", operationId = "searchAirportsByCity")
    @GetMapping("/byCity/{city}")
    public ResponseEntity<List<Airport>> searchAirportsByCity(@PathVariable String city) {
        List<Airport> airports = airportService.getAirportsByCity(city);

        return ResponseEntity.ok(airports);
    }

    @Operation(summary = "Search for airports by id.", operationId = "searchAirportByAirportId")
    @GetMapping("/byId/{airportId}")
    public ResponseEntity<Optional<Airport>> searchAirportByAirportId(@PathVariable Long airportId) {
        Optional <Airport> airport = airportService.getAirportById(airportId);

        return ResponseEntity.ok(airport);
    }
}
