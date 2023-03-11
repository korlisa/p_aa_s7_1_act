package app.setters;

import app.entities.Passenger;
import app.entities.User;
import app.services.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;


@Component
public class AdminRoleSetter implements ApplicationRunner {
    private UserService userService;
    public AdminRoleSetter(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        User admin = new User("123@mail.ru", "123", new HashSet<>(),
                new Passenger());
        userService.save(admin);
    }
}
