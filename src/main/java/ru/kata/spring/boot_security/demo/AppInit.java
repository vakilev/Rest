package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class AppInit {
    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {

        if (userService.getAllRoles().size() < 1) {
            System.out.println("start.......!!!");
            Role admin = new Role(1L, "ROLE_ADMIN");
            Role user = new Role(2L, "ROLE_USER");
            userService.addRole(admin);
            userService.addRole(user);
            Set<Role> roles = new HashSet<>();


            roles.add(user);
            User simpleUser = new User("user", "userov", "user@mail.ru", (byte) 12, "123");
            simpleUser.setRoles(roles);


            roles = new HashSet<>();
            roles.add(user);
            roles.add(admin);
            User adminUser = new User("admin", "adminov", "admin@mail.ru", (byte) 12, "123");
            adminUser.setRoles(roles);


            userService.addUser(simpleUser);
            userService.addUser(adminUser);

            System.out.println("hello, I have just create few users: \n" +
                    "username: user@mail.ru      password: 123 \n" +
                    "username: admin@mail.ru      password: 123 ");
        }
    }
}