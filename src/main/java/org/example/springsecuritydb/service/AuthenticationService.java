package org.example.springsecuritydb.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springsecuritydb.dto.RegistrationRequest;
import org.example.springsecuritydb.entity.User;
import org.example.springsecuritydb.repository.RoleRepository;
import org.example.springsecuritydb.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void register(@Valid RegistrationRequest request) {
        var userRole = roleRepository.findRoleByName("USER")
                .orElseThrow(()-> new IllegalStateException("Role USER was not initialized"));

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);

    }


}
