//package app;
//
//import app.entities.Passenger;
//import app.entities.Passport;
//import app.repositories.PassengerRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.json.JSONObject;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
////Тесты REST-контроллера для пассажира (тестовые пассажиры удаляются из БД в конце каждого метода)
//@SpringBootTest
//@AutoConfigureMockMvc(addFilters = false) // added (addFilters = false)
//public class PassengerRESTControllerTests {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//    @Autowired
//    private PassengerRepository repository;
//    @Autowired
//    private MockMvc mockMvc;
//
//    // Метод для создания тестового пассажира и сохранения его в БД, используется в методах тестов
//    private Passenger createTestPassenger(String firstName, String lastName, String phoneNumber,
//                                          String email, String middleName, LocalDate dateOfBirth,
//                                          Passport passport) {
//        Passenger passenger = new Passenger(firstName, lastName, phoneNumber, email, middleName, dateOfBirth, passport);
//        return repository.save(passenger);
//    }
//
//
//    @Test
//    public void givenPassengers_whenGetPassengers_thenStatus200() throws Exception {
//
//        Passenger p1 = createTestPassenger("Alina", "Lisova", "89772666342", "lisova@mail.ru",
//                "Evgenevna", LocalDate.of(1996, 12, 22), new Passport("34567875",
//                        "22.12.2040", "usa"));
//        Passenger p2 = createTestPassenger("Vika", "Rodina", "89772666342", "rodina@mail.ru",
//                "Vladimirovna", LocalDate.of(1995, 07, 24), new Passport("8646473",
//                        "22.12.2040", "usa"));
//
//        mockMvc.perform(
//                        get("/api/passenger/all"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(p1, p2))));
//
//        repository.delete(p1);
//        repository.delete(p2);
//    }
//
//
//    @Test
//    public void givenPassenger_whenAdd_thenStatus201andPassengerReturned() throws Exception {
//        MvcResult result = mockMvc.perform(
//                        post("/api/passenger/create")
//                                .content(objectMapper.writeValueAsString(new Passenger("Alina", "Lisova",
//                                        "89772666342", "lisova@mail.ru",
//                                        "Evgenevna", LocalDate.of(1996, 12, 22),
//                                        new Passport("34567875",
//                                                "22.12.2040", "usa"))))
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$.id").isNumber())
//                .andExpect(jsonPath("$.firstName").value("Alina")).andReturn();
//        try {
//            JSONObject passengerJson = new JSONObject(result.getResponse().getContentAsString());
//            repository.deleteById(passengerJson.getLong("id"));
//        } catch (Exception ignored) {
//        }
//    }
//
//    @Test
//    public void givenId_whenGetExistingPassenger_thenStatus200andPassengerReturned() throws Exception {
//        long id = createTestPassenger("Michail", "Shablonov", "89772666342",
//                "misha@mail.ru", "Aleksandrovich", LocalDate.of(1979, 1, 10),
//                new Passport("34567875",
//                        "22.12.2040", "usa")).getId();
//        mockMvc.perform(
//                        get("/api/passenger/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.firstName").value("Michail"));
//        repository.deleteById(id);
//    }
//
//
//    @Test
//    public void givePassenger_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {
//
//        Passenger oldPassenger = createTestPassenger("Nick", "Shablonov", "89772666342",
//                "misha@mail.ru", "Aleksandrovich", LocalDate.of(1979, 1, 10),
//                new Passport("3456788", "2070-12-03", "russian"));
//
//        Passenger passenger = new Passenger("Michail",
//                "Shablonov", "89772666342", "misha@mail.ru",
//                "Aleksandrovich", LocalDate.of(1979, 1, 10),
//                oldPassenger.getPassport());
//        passenger.setId(oldPassenger.getId());
//
//        mockMvc.perform(put("/api/passenger/edit").content(objectMapper.writeValueAsString(passenger))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(oldPassenger.getId()))
//                .andExpect(jsonPath("$.firstName").value("Michail"));
//        repository.deleteById(oldPassenger.getId());
//    }
//
//    @Test
//    public void givenPassenger_whenDeletePassenger_thenStatus200() throws Exception {
//
//        long id = createTestPassenger("Alina", "Lisova", "89772666342", "lisova@mail.ru",
//                "Evgenevna", LocalDate.of(1996, 12, 22), new Passport("34567875",
//                        "22.12.2040", "usa")).getId();
//
//        mockMvc.perform(
//                        delete("/api/passenger/{id}/delete", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id));
//    }
//}
//
//
