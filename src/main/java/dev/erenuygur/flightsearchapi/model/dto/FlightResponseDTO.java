package dev.erenuygur.flightsearchapi.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightResponseDTO {
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureDateTime;
    private Double price;
}