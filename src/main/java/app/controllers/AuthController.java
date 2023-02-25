package app.controllers;

import app.entities.Role;
import app.entities.User;
import app.services.RoleService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

//Пока не рабочее
@Controller
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AuthController(PasswordEncoder passwordEncoder, UserService userService, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String registrationAdmin(@ModelAttribute("user") User user, Model model){
        model.addAttribute("listOfRoles", roleService.getRoles());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam("listRoles") Collection<Role> roles, User formUser, Model model) {
        String err = "Пароли не совпадают";
        User user = new User();
//        user.setFirstName(formUser.getFirstName());
//        user.setLastName(formUser.getLastName());
//        user.setAge(formUser.getAge());
//        user.setUsername(formUser.getUsername());
        user.setEmail(formUser.getEmail());
        user.setPassword(passwordEncoder.encode(formUser.getPassword()));
        user.setRoles(roles);
        if (formUser.getPassword().equals(formUser.getConfirm())) {
            if (userService.loadUserByUsername(user.getUsername()) == null) {
                userService.save(user);
                return "redirect:/login";
            }
            err = "Email занят";
        }
        model.addAttribute("error", err);
        return "registration";
    }
}
