package com.pipiolo.springboot.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignupRequest {

    @NotBlank
    private final String name;

    @NotBlank
    private final String email;

    @NotBlank
    private final String password;
}
