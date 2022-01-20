package nl.eindopdracht.autogarage.security.service;

import nl.eindopdracht.autogarage.security.model.Role;
import nl.eindopdracht.autogarage.security.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();
}
