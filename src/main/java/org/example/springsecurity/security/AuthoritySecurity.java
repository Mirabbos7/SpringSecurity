package org.example.springsecurity.security;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.springsecurity.entities.Authority;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@ToString
public class AuthoritySecurity implements GrantedAuthority {

    private final Authority authority;

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
