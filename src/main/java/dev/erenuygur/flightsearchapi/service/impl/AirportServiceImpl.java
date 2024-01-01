package dev.erenuygur.flightsearchapi.service.impl;

import dev.erenuygur.flightsearchapi.model.entity.Airport;
import dev.erenuygur.flightsearchapi.repository.AirportRepository;
import dev.erenuygur.flightsearchapi.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Optional<Airport> getAirportById(Long id) {
        return airportRepository.findById(id);
    }

    @Override
    public List<Airport> getAirportsByCity(String city) {
        return airportRepository.findByCity(city);
    }

    @Override
    @Transactional
    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    @Transactional
    public Airport updateAirport(Long id, Airport airport) {
        if (airportRepository.existsById(id)) {
            airport.setId(id);
            return airportRepository.save(airport);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }
}
