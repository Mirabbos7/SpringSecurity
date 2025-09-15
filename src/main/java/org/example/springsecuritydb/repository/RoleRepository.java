package org.example.springsecuritydb.repository;

import org.example.springsecuritydb.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findRoleByName(String role);
}
