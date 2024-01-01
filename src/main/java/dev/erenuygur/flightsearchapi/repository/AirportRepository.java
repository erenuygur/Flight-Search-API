package dev.erenuygur.flightsearchapi.repository;

import dev.erenuygur.flightsearchapi.model.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    List<Airport> findByCity(String city);

    Airport getAirportByCity(String city);
}
