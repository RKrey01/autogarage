package nl.eindopdracht.autogarage.config;

import nl.eindopdracht.autogarage.security.model.Role;
import nl.eindopdracht.autogarage.security.model.User;
import nl.eindopdracht.autogarage.security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class UserRolesConfig {

    @Bean
    CommandLineRunner run(UserService userService) throws Exception {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.saveUser(new User(null, "Jan Janssen", "jan", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Pietje Piet", "piet", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Bill Gates", "bill", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Admin User", "admin", "admin123", new ArrayList<>()));

            userService.addRoleToUser("jan", "ROLE_USER");
            userService.addRoleToUser("piet", "ROLE_USER");
            userService.addRoleToUser("bill", "ROLE_USER");
            userService.addRoleToUser("admin", "ROLE_USER");
            userService.addRoleToUser("admin", "ROLE_ADMIN");

        };
    }
}

