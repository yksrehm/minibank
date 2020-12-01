package com.mherscode.minibank.services;

import com.mherscode.minibank.model.Role;
import com.mherscode.minibank.model.User;
import com.mherscode.minibank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(new User());
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(user1 -> userRepository.delete(user1));
    }

    public void save(User user) {

        userRepository.save(user);
    }

    public boolean hasRole(User user) {
        return (user.getRoles().stream().anyMatch(a -> a.getRoleName().equals(ROLE_USER)));
    }

    public String getUserRoleDto(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Role role : user.getRoles()) {

            if (stringBuilder.length() != 0)
                stringBuilder.append(", ");
            stringBuilder.append(role.getRoleName());
        }
        return stringBuilder.toString();
    }

}