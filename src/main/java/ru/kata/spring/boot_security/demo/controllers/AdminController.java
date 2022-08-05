package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String allUsers(@AuthenticationPrincipal User principal, @ModelAttribute("user") User user, Model model) {
        model.addAttribute("principal", principal);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("listRoles", userService.getAllRoles());
        return "admin";
    }


    @PostMapping
    public String addUser(@RequestParam("rolesId") String rolesId, @ModelAttribute("user") User user) {
        user.setRoles(userService.findRollsbyId(rolesId));
        userService.addUser(user);
        return "redirect:/admin";
    }


    @PatchMapping
    public String changeUser(@RequestParam("id") long id, @RequestParam("name") String name, @RequestParam("surname") String surname,
                             @RequestParam("age") byte age, @Validated @RequestParam("email") String email, @RequestParam(value = "password", required = false) String password,
                             @RequestParam(value = "rolesId", required = false) String rolesId, @ModelAttribute("user") User user) {
        user = new User(id, name, surname, age, email, password);
        if (rolesId != null) {
            user.setRoles(userService.findRollsbyId(rolesId));
        }
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping
    public String deleteUser(@RequestParam("id") long id, @ModelAttribute("user") User user) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


}
