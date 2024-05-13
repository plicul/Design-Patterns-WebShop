package com.designpatternproject.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "Korisnicko ime nije upisano.")
    private String username;
    @NotBlank(message = "Lozinka nije upisana.")
    private String password;
    private Long terminalId;
}