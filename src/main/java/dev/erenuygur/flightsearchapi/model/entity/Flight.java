package dev.erenuygur.flightsearchapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "departure_date_time")
    private LocalDateTime departureDateTime;

    @Column(name = "departure_airport_id")
    private Long departureAirportId;

    @Column(name = "arrival_airport_id")
    private Long arrivalAirportId;

    @Column(name = "price")
    private Double price;
}
