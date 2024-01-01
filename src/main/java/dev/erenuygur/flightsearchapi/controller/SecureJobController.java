package dev.erenuygur.flightsearchapi.controller;

import dev.erenuygur.flightsearchapi.job.FlightScheduledJob;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class SecureJobController {

    private final FlightScheduledJob flightScheduledJob;

    @Autowired
    public SecureJobController(FlightScheduledJob flightScheduledJob) {
        this.flightScheduledJob = flightScheduledJob;
    }

    @Operation(summary = "Trigger the flight scheduled job manually.")
    @GetMapping("/triggerJob")
    public String triggerJob() {
        flightScheduledJob.processJsonFile();
        return "Job triggered manually.";
    }
}