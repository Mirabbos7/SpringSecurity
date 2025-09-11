package org.example.springsecuritydb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationDto {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
