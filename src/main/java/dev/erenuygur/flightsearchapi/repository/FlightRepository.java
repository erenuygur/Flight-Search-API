package dev.erenuygur.flightsearchapi.repository;

import dev.erenuygur.flightsearchapi.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTimeGreaterThanEqual(Long departureAirportId, Long arrivalAirportId, LocalDateTime departureDateTime);
}
