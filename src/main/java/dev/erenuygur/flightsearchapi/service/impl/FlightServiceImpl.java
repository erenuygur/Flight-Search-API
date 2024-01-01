package dev.erenuygur.flightsearchapi.service.impl;

import dev.erenuygur.flightsearchapi.exception.AirportNotFoundException;
import dev.erenuygur.flightsearchapi.model.dto.FlightResponseDTO;
import dev.erenuygur.flightsearchapi.model.entity.Airport;
import dev.erenuygur.flightsearchapi.model.entity.Flight;
import dev.erenuygur.flightsearchapi.repository.AirportRepository;
import dev.erenuygur.flightsearchapi.repository.FlightRepository;
import dev.erenuygur.flightsearchapi.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    private Long getAirportIdByCity(String city) {
        return airportRepository.getAirportByCity(city).getId();
    }

    private List<FlightResponseDTO> findFlights(Long departureAirportId, Long arrivalAirportId, LocalDateTime departureDateTime) {
        return flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTimeGreaterThanEqual(
                departureAirportId, arrivalAirportId, departureDateTime).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private List<FlightResponseDTO> combineFlights(List<FlightResponseDTO> outboundFlights, List<FlightResponseDTO> returnFlights) {
        List<FlightResponseDTO> combinedFlights = new ArrayList<>();
        combinedFlights.addAll(outboundFlights);
        combinedFlights.addAll(returnFlights);

        return combinedFlights;
    }

    public FlightResponseDTO convertToDTO(Flight flight) {

        Airport departureAirport = airportRepository.findById(flight.getDepartureAirportId())
                .orElseThrow(AirportNotFoundException::new);

        Airport arrivalAirport = airportRepository.findById(flight.getArrivalAirportId())
                .orElseThrow(AirportNotFoundException::new);

        String departureAirportCity = departureAirport.getCity();
        String arrivalAirportCity = arrivalAirport.getCity();

        FlightResponseDTO flightResponseDTO = new FlightResponseDTO();
        flightResponseDTO.setArrivalCity(arrivalAirportCity);
        flightResponseDTO.setDepartureCity(departureAirportCity);
        flightResponseDTO.setDepartureDateTime(flight.getDepartureDateTime());
        flightResponseDTO.setPrice(flight.getPrice());

        return flightResponseDTO;
    }

    @Override
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<FlightResponseDTO> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightResponseDTO> searchFlights(String departureCity, String arrivalCity, LocalDateTime departureDateTime, LocalDateTime returnDateTime) {
        var departureAirportId = getAirportIdByCity(departureCity);
        var arrivalAirportId = getAirportIdByCity(arrivalCity);

        if (returnDateTime == null) {
            return findFlights(departureAirportId, arrivalAirportId, departureDateTime);
        } else {
            var outboundFlights = findFlights(departureAirportId, arrivalAirportId, departureDateTime);
            var returnFlights = findFlights(arrivalAirportId, departureAirportId, returnDateTime);

            return combineFlights(outboundFlights, returnFlights);
        }
    }

    @Override
    public Flight updateFlight(Long id, Flight flight) {
        if (flightRepository.existsById(id)) {
            flight.setId(id);
            return flightRepository.save(flight);
        }
        return null;
    }
}
