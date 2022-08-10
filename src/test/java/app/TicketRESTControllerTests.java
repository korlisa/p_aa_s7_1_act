package app;

import app.entities.*;
import app.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketRESTControllerTests {


    //Тесты REST-контроллера для билета (тестовые билеты удаляются из БД в конце каждого метода)

        @Autowired
        private ObjectMapper objectMapper;
        @Autowired
        private TicketRepository ticketRepository;
        @Autowired
        private PassengerRepository passengerRepository;
        @Autowired
        private FlightRepository flightRepository;
        @Autowired
        private PassportRepository passportRepository;
        @Autowired
        private SeatRepository seatRepository;
        @Autowired
        private MockMvc mockMvc;

        // Метод для создания тестового билета и сохранения его в БД, используется в методах тестов
        private Ticket createTestTicket(String name) {
            Passenger p1 = new Passenger(name, name, "89772666342", name + "@mail.ru",
                    name, LocalDate.of(1996, 12, 22), new Passport("34567875",
                    "22.12.2040", "usa"));
            passengerRepository.save(p1);
            Flight flight = new Flight();
            flightRepository.save(flight);
            Seat seat = new Seat();
            seatRepository.save(seat);
            Ticket ticket = new Ticket(p1, flight, seat, Ticket.Subcategory.BASIC);
            return ticketRepository.save(ticket);
        }


        @Test
        public void givenTickets_whenGetTickets_thenStatus200() throws Exception {

            Ticket t1 = createTestTicket("test1");
            Ticket t2 = createTestTicket("test2");

            mockMvc.perform(
                            get("/api/ticket/all"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(t1, t2))));

            ticketRepository.delete(t1);
            ticketRepository.delete(t2);
        }


        @Test
        public void givenTicket_whenAdd_thenStatus201andTicketReturned() throws Exception {
            Passenger p1 = new Passenger("Alina", "Lisova", "89772666342", "lisova@mail.ru",
                    "Evgenevna", LocalDate.of(1996, 12, 22), new Passport("34567875",
                    "22.12.2040", "usa"));
            passengerRepository.save(p1);
            Flight flight = new Flight();
            flightRepository.save(flight);
            Seat seat = new Seat();
            seatRepository.save(seat);
            MvcResult result = mockMvc.perform(
                            post("/api/ticket/create")
                                    .content(objectMapper.writeValueAsString(new Ticket(p1, flight, seat, Ticket.Subcategory.BASIC)))
                                    .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.passenger.firstName").value("Alina")).andReturn();
            try {
                JSONObject ticketJson = new JSONObject(result.getResponse().getContentAsString());
                ticketRepository.deleteById(ticketJson.getLong("id"));
            } catch (Exception ignored) {
            }
        }

        @Test
        public void givenId_whenGetExistingTicket_thenStatus200andTicketReturned() throws Exception {
            long id = createTestTicket("test2").getId();
            mockMvc.perform(
                            get("/api/ticket/{id}", id))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(id))
                    .andExpect(jsonPath("$.passenger.firstName").value("test2"));
            ticketRepository.deleteById(id);
        }


        @Test
        public void giveTicket_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {

            Ticket oldTicket = createTestTicket("ticket1");
            Passenger p1 = new Passenger("Alina", "Lisova", "89772666342", "lisova@mail.ru",
                    "Evgenevna", LocalDate.of(1996, 12, 22), new Passport("34567875",
                    "22.12.2040", "usa"));
            passengerRepository.save(p1);
            Flight flight = new Flight();
            flightRepository.save(flight);
            Seat seat = new Seat();
            seatRepository.save(seat);
            Ticket ticket = new Ticket(p1, flight, seat, Ticket.Subcategory.BASIC);
            ticket.setId(oldTicket.getId());

            mockMvc.perform(put("/api/ticket/edit").content(objectMapper.writeValueAsString(ticket))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(oldTicket.getId()))
                    .andExpect(jsonPath("$.passenger.firstName").value("Alina"));
            ticketRepository.deleteById(oldTicket.getId());
        }

        @Test
        public void givenTicket_whenDeleteTicket_thenStatus200() throws Exception {

            long id = createTestTicket("test1").getId();

            mockMvc.perform(
                            delete("/api/ticket/{id}/delete", id))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(id));
        }
    }



