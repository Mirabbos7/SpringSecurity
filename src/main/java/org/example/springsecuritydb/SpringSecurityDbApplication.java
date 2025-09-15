package org.example.springsecuritydb;

import org.example.springsecuritydb.entity.Role;
import org.example.springsecuritydb.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringSecurityDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDbApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findRoleByName("USER").isEmpty()) {
                roleRepository.save(
                        Role.builder()
                                .name("USER")
                                .build()
                );
            }
        };
    }
}
