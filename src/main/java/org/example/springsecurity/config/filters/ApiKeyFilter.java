package org.example.springsecurity.config.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.springsecurity.config.authentication.ApiKeyAuthentication;
import org.example.springsecurity.config.manager.CustomAuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    private final String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        CustomAuthenticationManager ca = new CustomAuthenticationManager(key);
        var requestKey = request.getHeader("x-api-key");

        if("null".equals(requestKey) || requestKey == null){
            filterChain.doFilter(request, response);
            return;
        }
        var auth = new ApiKeyAuthentication(requestKey);
        try {
            var a = ca.authenticate(auth);
            if(a.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(a);
                filterChain.doFilter(request, response);
            }else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (AuthenticationException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
