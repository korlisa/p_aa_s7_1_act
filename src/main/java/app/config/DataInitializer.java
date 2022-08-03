package app.config;


import app.entities.Destination;
import app.entities.Role;
import app.entities.User;
import app.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;


@AllArgsConstructor
@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;
    private final DestinationService destinationService;
    private final AircraftService aircraftService;


    @PostConstruct
    public void init() {
        initRoles();
        createUserForSpringSecurity();
        createDestination();
        createAircraft();
        System.out.println("DataInitializer сработал!");
    }

    /**
     * Method for initialization destination
     */
    public void createDestination() {
        Destination destination = new Destination("New York", "United States", "America");
        Destination destination2 = new Destination("Cairo", "Egypt", "Africa");
        Destination destination3 = new Destination("Stockholm", "Sweden", "Europe");

        destinationService.saveDestination(destination);
        destinationService.saveDestination(destination2);
        destinationService.saveDestination(destination3);
    }

    /**
     * Метод для инициализации ролей
     */
    public void initRoles () {
        Role admin =new Role(1L,"ROLE_ADMIN");
        Role manager =new Role(2L,"ROLE_MANAGER");
        Role passenger =new Role(3L,"ROLE_PASSENGER");
        roleService.saveRole(admin);
        roleService.saveRole(manager);
        roleService.saveRole(passenger);
    }

    /**
     * Метод для создания юзера для SpringSecurity
     */
    public void createUserForSpringSecurity(){
        User user = new User();
        user.setEmail("admin@email.com");
        user.setPassword("admin");
        user.addRoleToCollection(roleService.findRoleByName("ROLE_ADMIN"));
        userService.saveUser(user);
    }

    /**
     * Create 3 Aircraft in the database
     *
     * @author Eugene Kolyshev
     */

    public void createAircraft() {

        aircraftService.save(new Fleet().createBoeing737());
        aircraftService.save(new Fleet().createAirbusA321());
        aircraftService.save(new Fleet().createEmbraer170());

    }

}
