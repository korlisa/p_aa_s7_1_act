package app.services;


import app.entities.Admin;
import app.entities.AirlineManager;
import app.entities.Passenger;
import app.entities.User;
import app.repositories.PassengerRepository;
import app.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для User нужен для SpringSecurity
 *
 * @author Minibaeva Elvira
 */

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PassengerRepository passengerRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    /**
     *  Метод сохранения юзера в бд
     */
    public void saveUser (User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    /**Метод сохранения пользователя в зависимости от его класса.
     * Используется на странице admin.html при изменении пользователя.
     *
     * @param user
     */
    public void saveUserFromController(User user) {
        if (userRepository.findUserById(user.getId()).getClass().toString().contains("Admin")) {
            Admin admin = new Admin();
            admin.setId(user.getId());
            admin.setEmail(user.getEmail());
            admin.setRoles(user.getRoles());
            admin.setPassword(passwordEncoder.encode(user.getPassword()));
            admin.addRoleToCollection(roleService.findRoleByName("ROLE_ADMIN"));
            userRepository.save(admin);
        } else {
            AirlineManager manager = new AirlineManager();
            manager.setId(user.getId());
            manager.setEmail(user.getEmail());
            manager.setRoles(user.getRoles());
            manager.setPassword(passwordEncoder.encode(user.getPassword()));
            manager.addRoleToCollection(roleService.findRoleByName("ROLE_MANAGER"));
            userRepository.save(manager);
        }
    }
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findUserById(id);
    }

    /**
     *  Метод для аутентификации пользователей
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", email));
        }
        return new User(user.getUsername(), user.getPassword(),
                user.getRoles());

    }

    public void deleteAll() {
        userRepository.deleteAll();
    }


    public Passenger findPassengerByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return passengerRepository.findPassengerById(user.getId());
    }
}
