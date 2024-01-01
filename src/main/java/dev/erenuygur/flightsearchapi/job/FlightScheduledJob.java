package dev.erenuygur.flightsearchapi.job;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.erenuygur.flightsearchapi.model.entity.Flight;
import dev.erenuygur.flightsearchapi.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class FlightScheduledJob {

    private final FlightRepository flightRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void processJsonFile() {
        String jsonFilePath = "src/main/resources/flights_mock_data.json";

        try {
            List<Flight> flights = readJsonFile(jsonFilePath);
            flightRepository.saveAll(flights);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Flight> readJsonFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objectMapper.registerModule(javaTimeModule);

        File file = new File(filePath);

        return objectMapper.readValue(file, new TypeReference<List<Flight>>() {});
    }

}