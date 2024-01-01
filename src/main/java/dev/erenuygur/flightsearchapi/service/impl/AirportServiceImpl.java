package dev.erenuygur.flightsearchapi.service.impl;

import dev.erenuygur.flightsearchapi.exception.AirportNotFoundException;
import dev.erenuygur.flightsearchapi.model.entity.Airport;
import dev.erenuygur.flightsearchapi.repository.AirportRepository;
import dev.erenuygur.flightsearchapi.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).orElseThrow(AirportNotFoundException::new);
    }

    @Override
    public List<Airport> getAirportsByCity(String city) {
        return airportRepository.findByCity(city);
    }

    @Override
    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport updateAirport(Long id, Airport airport) {
        if (airportRepository.existsById(id)) {
            airport.setId(id);
            return airportRepository.save(airport);
        }
        return null;
    }

    @Override
    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }
}
