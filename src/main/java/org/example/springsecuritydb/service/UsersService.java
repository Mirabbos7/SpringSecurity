package org.example.springsecuritydb.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.springsecuritydb.entities.User;
import org.example.springsecuritydb.repository.RoleRepository;
import org.example.springsecuritydb.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .toList()
        );
    }

    public void createUser(User users) {
        users.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        userRepository.save(users);
    }
}
