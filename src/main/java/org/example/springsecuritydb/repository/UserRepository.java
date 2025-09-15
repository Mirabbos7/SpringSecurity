package org.example.springsecuritydb.repository;

import org.example.springsecuritydb.entity.Role;
import org.example.springsecuritydb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}

