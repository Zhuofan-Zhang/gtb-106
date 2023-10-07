package com.thoughtworks.capability.gtb.bspringsecuritydemo.service;

import com.thoughtworks.capability.gtb.bspringsecuritydemo.model.Authority;
import com.thoughtworks.capability.gtb.bspringsecuritydemo.model.User;
import com.thoughtworks.capability.gtb.bspringsecuritydemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;
import static org.springframework.security.core.userdetails.User.withUsername;

@Service
@AllArgsConstructor
public class CustomUsersDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));

        final var auths = user.getAuthorities().stream()
                .map(Authority::getAuthority)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(auths)
                .disabled(!TRUE.equals(user.getEnabled()))
                .build();
    }
}
