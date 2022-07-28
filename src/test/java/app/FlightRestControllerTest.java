package app;

import app.entities.Aircraft;
import app.entities.Destination;
import app.entities.Flight;
import app.entities.Category;
import app.repositories.AircraftRepository;
import app.repositories.DestinationRepository;
import app.repositories.FlightRepository;
import app.services.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * test FlightRestController with MockMVC
 *
 * TODO * обновить тесты после добавления сущностей Seat, Category, Destination и Aircraft
 *
 * @author - Alexander PLekhov
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FlightRestControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FlightService flightService;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private AircraftRepository aircraftRepository;

    @AfterEach
    public void resetDB(){
        flightRepository.deleteAll();
        destinationRepository.deleteAll();
        aircraftRepository.deleteAll();
    }

    private Flight createTestFlight() {
        Destination moscow = new Destination("Moscow");
        Destination kiev = new Destination("Kiev");
        Aircraft boeing = new Aircraft("Boeing");

        destinationRepository.save(moscow);
        destinationRepository.save(kiev);
        aircraftRepository.save(boeing);

        Flight flight = new Flight();
        flight.setFrom(destinationRepository.findDestinationByName("Moscow"));
        flight.setTo(destinationRepository.findDestinationByName("Kiev"));
        flight.setDepartureDateTime(LocalDateTime.of(2022, Month.AUGUST, 9, 13, 00, 00));
        flight.setArrivalDateTime(LocalDateTime.of(2022, Month.AUGUST, 9, 15, 00, 00));
        flight.setAircraft(aircraftRepository.findAircraftByName("Boeing"));
        flight.setFlightStatus(Flight.FlightStatus.ON_TIME);

        flightService.save(flight);

        return flight;
    }

    @Test
    public void whenCreateFlight_thenStatus201() throws Exception {
        Flight flight = createTestFlight();
        mockMvc.perform(
                post("/api/flights")
                        .content(objectMapper.writeValueAsString(flight))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    public void whenUpdateFlight_thenStatus200() throws Exception {
        Flight flight = createTestFlight();
        Aircraft airbus = new Aircraft("Airbus");
        aircraftRepository.save(airbus);
        flight.setAircraft(aircraftRepository.findAircraftByName("Airbus"));

        mockMvc.perform(
                put("/api/flights")
                        .content(objectMapper.writeValueAsString(flight))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.aircraft.name").value("Airbus"));
    }
    @Test
    public void whenDeleteFlight_thenStatus200() throws Exception {
        Flight flight = createTestFlight();
        long id = flight.getId();

        mockMvc.perform(
                delete("/api/flights/{id}", id)
                        .content(objectMapper.writeValueAsString(flight))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void whenGetFlightById_thenStatus200() throws Exception {
        Flight flight = createTestFlight();
        long id = flight.getId();

        mockMvc.perform(
                get("/api/flights/{id}", id)
                        .content(objectMapper.writeValueAsString(flight))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    public void whenGetFlightByFromToDate_thenStatus200() throws Exception {
        Flight flight = createTestFlight();
        String from = "Moscow";
        String to = "Kiev";
        String date = "2022-08-09";

        mockMvc.perform(
                get("/api/flights/{from}/{to}/{date}", from, to, date)
                        .content(objectMapper.writeValueAsString(flight))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(flight.getId()))
                .andExpect(jsonPath("$[0].from.name").value("Moscow"))
                .andExpect(jsonPath("$[0].to.name").value("Kiev"));

    }

    // проверить после добавления сущностей Seat и Category
    @Test
    public void whenGetAllFreeSeatsOnFlight_thenStatus200() throws Exception {
        Flight flight = createTestFlight();

        mockMvc.perform(
                get("/api/flights/allFree")
                        .content(objectMapper.writeValueAsString(flight))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isRegistered").value(false))
                .andExpect(jsonPath("$[0].isSold").value(false));
    }

    // проверить после добавления сущностей Seat и Category
    @Test
    public void whenGetAllFreeSeatsOnFlightByEconomy_thenStatus200() throws Exception {
        Flight flight = createTestFlight();

        mockMvc.perform(
                        get("/api/flights/allFreeEconomy")
                                .content(objectMapper.writeValueAsString(flight))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].category").value(Category.ECONOMY))
                .andExpect(jsonPath("$[0].isRegistered").value(false))
                .andExpect(jsonPath("$[0].isSold").value(false));
    }

    // проверить после добавления сущности Seat и Category
    @Test
    public void whenGetAllFreeSeatsOnFlightByBusiness_thenStatus200() throws Exception {
        Flight flight = createTestFlight();

        mockMvc.perform(
                        get("/api/flights/allFreeBusiness")
                                .content(objectMapper.writeValueAsString(flight))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].category").value(Category.BUSINESS))
                .andExpect(jsonPath("$[0].isRegistered").value(false))
                .andExpect(jsonPath("$[0].isSold").value(false));
    }
}

