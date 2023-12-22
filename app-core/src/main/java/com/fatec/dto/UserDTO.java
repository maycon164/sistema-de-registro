package com.fatec.dto;

import com.fatec.model.enums.LabelEnum;
import com.fatec.model.enums.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserDTO (
        @NotNull
        @NotEmpty
        String name,
        @NotNull
        @Email
        String email,
        @NotNull
        RoleEnum role,
        String jobPosition,

        Long teamId
){ }
