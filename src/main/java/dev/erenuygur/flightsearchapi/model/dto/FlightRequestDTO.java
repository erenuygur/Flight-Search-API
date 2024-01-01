package dev.erenuygur.flightsearchapi.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.erenuygur.flightsearchapi.util.deserializer.LocalDateTimeDeserializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightRequestDTO {

    private String departureCity;
    private String arrivalCity;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime departureDateTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime returnDateTime;

}
