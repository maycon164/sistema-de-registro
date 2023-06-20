package com.fatec.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO (
        @NotNull
        @NotEmpty
        String name,

        @NotNull
        @Email
        String email
){ }
