package org.example.springsecuritydb.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotNull(message = "username is mandatory")
    @NotBlank(message = "username is mandatory")
    private String username;

    @Email(message = "Email is not formatted -> mirabbos@gmail")
    @Column(unique = true)
    @NotNull(message = "email is mandatory")
    @NotBlank(message = "username is mandatory")
    private String email;

    @NotNull(message = "password is mandatory")
    @NotBlank(message = "username is mandatory")
    private String password;
}
