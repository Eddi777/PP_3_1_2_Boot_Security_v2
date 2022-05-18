package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;
    public AdminController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userServiceImpl.findAll();
        model.addAttribute("users", users);
        return "admin-main";
    }

    @GetMapping(value = "/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping(value="/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userServiceImpl.findById(id);
        model.addAttribute("user", user);
        return "admin_user-update";
    }
    @PostMapping(value = "/user-update")
    public String updateUser(User user) {
        userServiceImpl.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value="/user-create")
    public String createUserForm(User user) {
        return "admin_user-create";
    }
    @PostMapping(value = "/user-create")
    public String createUser(User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/admin";
    }

}
