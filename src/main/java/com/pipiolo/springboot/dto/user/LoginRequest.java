package com.pipiolo.springboot.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    private final String email;

    @NotBlank
    private final String password;
}
