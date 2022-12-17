package app;


import app.entities.*;
import app.services.RoleService;
import app.services.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

/** Test AdminRestController with MockMVC
 *
 * @author Komarov Rostislav
 */

@SpringBootTest
@AutoConfigureMockMvc
public class AdminApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void addRolesToDatabase() {
        roleService.saveRole(new Role("ROLE_ADMIN"));
        roleService.saveRole(new Role("ROLE_MANAGER"));
        roleService.saveRole(new Role("ROLE_PASSENGER"));
    }

    @AfterEach
    public void deleteRolesFromDatabase() {
        roleService.deleteRoleAll();
        userService.deleteAll();
    }

    /**
     * Method testing getting every user existing in DB,
     * successful request have to throw 200 OK status with content type application_json and with users themselves
     * @throws Exception
     */
    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void givenThreeUsers_whenGettingAllUsers_thenStatus200andUsersReturned() throws Exception {
        Admin admin = createTestAdminUser_thenAddToDatabase();
        AirlineManager manager = createTestManagerUser_thenAddToDatabase();
        Passenger passenger = createTestPassengerUser_thenAddToDatabase();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(List.of(admin, manager, passenger))));
    }

    /**
     * Method testing case when fetching users without any existing there.
     * Have to throw 204 No Content status and "[]" in response body
     * @throws Exception
     */
    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void gettingAllUsers_whenNoUserInDatabase_thenStatus204NoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/users"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    @WithMockUser(roles = {"PASSENGER"})
    public void gettingAllUsers_whenUserIsNotAdmin_thenStatus403forbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/users"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void givenThreeUsers_whenGettingProperUserById_thenStatus200andUserReturned() throws Exception {
        Passenger passenger = createTestPassengerUser_thenAddToDatabase();
        Long passengerId = passenger.getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/users/{id}", passengerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(passenger)));
    }

    @Test
    public void gettingWrongUser_thenStatus404NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/users/54"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"PASSENGER"})
    public void givenThreeUsers_gettingUser_whenUserIsNotAdmin_thenStatus403forbidden() throws Exception {
        Passenger passenger = createTestPassengerUser_thenAddToDatabase();
        Long passengerId = passenger.getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/users/{id}", passengerId))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    /**
     * Method creating new Admin instance fetch it into DB and return it
     * @return {@link app.entities.Admin}
     */
    private Admin createTestAdminUser_thenAddToDatabase() {
        Admin admin = new Admin();
        admin.setEmail("testAdminEmail@gmail.com");
        admin.setPassword("adminpassword");
        admin.addRoleToCollection(roleService.findRoleByName("ROLE_ADMIN"));
        userService.saveUser(admin);
        return admin;
    }

    /**
     * Method creating new AirlineManager instance fetch it into DB and return it
     * @return {@link app.entities.AirlineManager}
     */
    private AirlineManager createTestManagerUser_thenAddToDatabase() {
        AirlineManager manager = new AirlineManager();
        manager.setEmail("testAdminEmail@gmail.com");
        manager.setPassword("adminpassword");
        manager.addRoleToCollection(roleService.findRoleByName("ROLE_ADMIN"));
        userService.saveUser(manager);
        return manager;
    }

    /**
     * Method creating new Passenger instance fetch it into DB and return it
     * @return {@link app.entities.Passenger}
     */
    private Passenger createTestPassengerUser_thenAddToDatabase() {
        Passenger passenger = new Passenger();
        passenger.setEmail("testAdminEmail@gmail.com");
        passenger.setPassword("adminpassword");
        passenger.addRoleToCollection(roleService.findRoleByName("ROLE_ADMIN"));
        userService.saveUser(passenger);
        return passenger;
    }

}
