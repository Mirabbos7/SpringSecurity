package org.example.springsecurity.controllers;

import lombok.AllArgsConstructor;
import org.example.springsecurity.entities.User;
import org.example.springsecurity.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class DemoController {

    private final UserService userService;

    @GetMapping("hello")
    public String hello(){
        var u = SecurityContextHolder.getContext().getAuthentication();
        u.getAuthorities().forEach(a-> System.out.println(a));
        return "hello";
    }
}
