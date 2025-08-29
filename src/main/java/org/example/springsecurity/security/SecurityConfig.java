package org.example.springsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .and()
                .authorizeRequests()
                .requestMatchers(HttpMethod.GET, "/demo/**").hasAuthority("read")
                .anyRequest().authenticated()
//                .anyRequest().authenticated()
//                .anyRequest().permitAll()
//                .anyRequest().hasAuthority("read")
//                .anyRequest().hasAnyAuthority("read", "write")
//                .anyRequest().hasAnyRole("ADMIN", "MANAGER")

                .and().csrf().disable() // DON'T DO IT IN REAL-WORLD APPLICATIONS
                .build();

        //matcher method + authorization rule
        // 1. which matcher method should you use and how
        // 2. how to apply different authorization rules
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var uds = new InMemoryUserDetailsManager();
        var u1 = User.withUsername("bill")
                .password(passwordEncoder().encode("123456"))
                .authorities("read")
//                .roles("ADMIN")
                .build();
        uds.createUser(u1);

        var u2 = User.withUsername("john")
                .password(passwordEncoder().encode("123456"))
                .authorities("write")
//                .roles("MANAGER")
                .build();
        uds.createUser(u2);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
