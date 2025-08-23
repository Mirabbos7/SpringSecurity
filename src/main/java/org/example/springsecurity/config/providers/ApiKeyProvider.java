package org.example.springsecurity.config.providers;

import lombok.AllArgsConstructor;
import org.example.springsecurity.config.authentication.ApiKeyAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@AllArgsConstructor
public class ApiKeyProvider implements AuthenticationProvider {
    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        ApiKeyAuthentication auth = (ApiKeyAuthentication) authentication;
        if (key.equals(auth.getKey())) {
            auth.setAuthenticated(true);
            return auth;
        }
            throw new BadCredentialsException("Wrong creds");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
