//package app;
//
//import app.entities.*;
//import app.services.RoleService;
//import app.services.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import java.util.Arrays;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//
///** Test AdminRestController with MocMVC
// *
// * @author Mnibaeva Elvira
// */
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc()
//@WithMockUser(username ="admin@email.com",password = "pass", roles =  {"ADMIN"})
//public class AdminApplicationTests {
//    @Autowired
//    private ObjectMapper objectMapper;
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    RoleService roleService;
//    @Autowired
//    UserService userService;
//
//
//    @AfterEach
//    public void resetDB(){
//        userService.deleteAll();
//        roleService.deleteAll();
//    }
//
//    private Admin createTestAdmin(String email) {
//        Admin admin = new Admin();
//        admin.setEmail(email);
//        admin.setPassword("admin");
//        admin.addRoleToCollection(roleService.findRoleByName("ROLE_ADMIN"));
//        userService.saveUser(admin);
//        return admin;
//    }
//
//    private AirlineManager createTestManager(String email) {
//        AirlineManager manager = new AirlineManager();
//        manager.setEmail(email);
//        manager.setPassword("manager");
//        manager.addRoleToCollection(roleService.findRoleByName("ROLE_MANAGER"));
//        userService.saveUser(manager);
//        return manager;
//    }
//
//    /**
//     * Admin
//     * @throws Exception
//     */
//    @Test
//    public void givenAdmin_whenAdd_thenStatus201andAdminReturned() throws Exception {
//        Admin admin = new Admin();
//        admin.setEmail("admin1@email.com");
//        admin.setPassword("admin");
//        admin.addRoleToCollection(roleService.findRoleByName("ROLE_ADMIN"));
//        mockMvc.perform(
//                        post("/api/create/admin")
//                                .content(objectMapper.writeValueAsString(admin))
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isCreated())
//                .andReturn().getResponse().getContentAsString();
//    }
//
//    /**
//     *  AirlineManager
//     * @throws Exception
//     */
//    @Test
//    public void givenManager_whenAdd_thenStatus201andManagerReturned() throws Exception {
//        AirlineManager manager = new AirlineManager();
//        manager.setEmail("manager1@email.com");
//        manager.setPassword("manager");
//        manager.addRoleToCollection(roleService.findRoleByName("ROLE_MANAGER"));
//        mockMvc.perform(
//                        post("/api/create/manager")
//                                .content(objectMapper.writeValueAsString(manager))
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isCreated())
//                .andReturn().getResponse().getContentAsString();;
//    }
//
//    /**
//     * id
//     * @throws Exception
//     */
//    @Test
//    public void givenId_whenGetExistingUser_thenStatus200andUserReturned() throws Exception {
//        AirlineManager manager = createTestManager("manager12@email.com");
//        long id = manager.getId();
//        mockMvc.perform(
//                        get("/api/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.email").value("manager12@email.com"));
//    }
//
//    /**
//     *
//     * @throws Exception
//     */
//    @Test
//    public void giveUser_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {
//        Admin admin = createTestAdmin("admin12345@email.com");
//        long id = admin.getId();
//        admin.setEmail("new@email.com");
//        mockMvc.perform(
//                        put("/api/edit")
//                                .content(objectMapper.writeValueAsString(admin))
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();;
//    }
//
//    /**
//     * id
//     * @throws Exception
//     */
//    @Test
//    public void givenUser_whenDeleteUser_thenStatus200() throws Exception {
//        Admin admin = createTestAdmin("admin12345@email.com");
//        long id = admin.getId();
//        mockMvc.perform(
//                        delete("/api/{id}", id)
//                .content(objectMapper.writeValueAsString(admin))
//                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();;
//    }
//
//    /**
//     *
//     * @throws Exception
//     */
//    @Test
//    public void givenUsers_whenGetUsers_thenStatus200() throws Exception {
//        AirlineManager manager = new AirlineManager();
//        manager.setEmail("testmanager12@email.com");
//        manager.setPassword("manager12");
//        manager.addRoleToCollection(roleService.findRoleByName("ROLE_MANAGER"));
//        userService.saveUser(manager);
//        Admin admin = new Admin();
//        admin.setEmail("testAdmin12@email.com");
//        admin.setPassword("admin12");
//        admin.addRoleToCollection(roleService.findRoleByName("ROLE_ADMIN"));
//        userService.saveUser(admin);
//
//        mockMvc.perform(
//                        get("/api/users"))
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//    }
//
//    /**
//     *
//     * @throws Exception
//     */
//    @Test
//    public void givenRole_whenAdd_thenStatus201andRoleReturned() throws Exception {
//        Role role = new Role("ROLE_USER");
//        mockMvc.perform(
//                        post("/api/roles/create")
//                                .content(objectMapper.writeValueAsString(role))
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isCreated())
//                .andReturn().getResponse().getContentAsString();;
//    }
//
//    /**
//     * id
//     * @throws Exception
//     */
//    @Test
//    public void givenId_whenGetExistingRole_thenStatus200andUserReturned() throws Exception {
//        Role role = new Role("ROLE_USER");
//        roleService.saveRole(role);
//        long id = role.getId();
//        mockMvc.perform(
//                        get("/api/roles/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value("ROLE_USER"));
//    }
//
//    /**
//     * id
//     * @throws Exception
//     */
//    @Test
//    public void givenRole_whenDeleteRole_thenStatus200() throws Exception {
//        Role role = new Role("ROLE_USER");
//        roleService.saveRole(role);
//        long id = role.getId();
//        mockMvc.perform(
//                        delete("/api/roles/{id}", id)
//                                .content(objectMapper.writeValueAsString(role))
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//    }
//
//    /**
//     *
//     * @throws Exception
//     */
//    @Test
//    public void givenRoles_whenGetRoles_thenStatus200() throws Exception {
//        Role passenger =new Role("ROLE_PASS");
//        Role role = new Role("ROLE_USER");
//        roleService.saveRole(role);
//        roleService.saveRole(passenger);
//
//        mockMvc.perform(
//                        get("/api/roles"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(passenger,role))));
//
//    }
//}
