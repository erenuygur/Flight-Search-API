package dev.erenuygur.flightsearchapi.controller;

import dev.erenuygur.flightsearchapi.job.FlightScheduledJob;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/job")
public class FlightJobController {

    private final FlightScheduledJob flightScheduledJob;

    @Operation(summary = "Trigger the flight scheduled job manually.")
    @PostMapping
    public String triggerJob() {
        flightScheduledJob.processJsonFile();
        return "Job triggered manually.";
    }
}