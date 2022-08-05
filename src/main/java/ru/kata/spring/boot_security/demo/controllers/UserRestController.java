package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> allUsers(@ModelAttribute("user") User user) {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id, @ModelAttribute("user") User user) {
        return userService.getUserById(id);
    }


    @PostMapping
    public User addUser(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }

    @PutMapping("/{id}")
    public User changeUser(@RequestBody User user) {
        userService.updateUser(user.getId(), user);
        return user;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "Ok";
    }

}
