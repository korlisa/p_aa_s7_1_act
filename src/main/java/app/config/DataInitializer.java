package app.config;

import app.entities.Role;
import app.entities.User;
import app.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;


/**
 * В этом классе инициализируются тестовые данные для базы.
 * Эти данные будут каждый раз создаваться заново при поднятии SessionFactory и удаляться из БД при её остановке.
 * Инжектьте и используйте здесь соответствующие сервисы ваших сущностей."
 */
@AllArgsConstructor
@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;
    private final AircraftService aircraftService;


    @PostConstruct
    public void init() {
        initRoles();
        createUserForSpringSecurity();
        createAircraft();

        System.out.println("DataInitializer сработал!");
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
