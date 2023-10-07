package com.thoughtworks.capability.gtb.bspringsecuritydemo.service;

import com.thoughtworks.capability.gtb.bspringsecuritydemo.exception.EntityNotFoundException;
import com.thoughtworks.capability.gtb.bspringsecuritydemo.model.Authority;
import com.thoughtworks.capability.gtb.bspringsecuritydemo.model.User;
import com.thoughtworks.capability.gtb.bspringsecuritydemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void registerUser(String username, String password) {
        final var user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        user.setAuthorities(Collections.singletonList(new Authority(username, "ROLE_NORMAL")));
        userRepository.save(user);
    }

    public void updateUser(String username, Boolean enabled) {
        final var user = userRepository.findById(username).orElseThrow(EntityNotFoundException::new);
        user.setEnabled(enabled);
        userRepository.save(user);
    }
}
