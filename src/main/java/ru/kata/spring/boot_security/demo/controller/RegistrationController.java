package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class RegistrationController {

    private final UserService userService;
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String openRegistrationForm(User user){
        System.out.println("open reg page");
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(User user, Model model) {
        User userFromBD = userService.getUserByUsername(user.getUsername());
        if (userFromBD != null) {
            model.addAttribute("message", "User exists !");
            return "redirect:/registration";
        }
        user.setActive(true);
//        user.addRole(new Role("ADMIN"));
        user.addRole(new Role("USER"));
        System.out.println("User added" + user);
        userService.saveUser(user);
        return "redirect:/user";
    }
}
