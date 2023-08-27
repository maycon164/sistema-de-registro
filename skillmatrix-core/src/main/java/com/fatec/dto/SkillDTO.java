package com.fatec.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SkillDTO(
        @NotNull
        @NotEmpty
        String name,
        @NotNull
        String description,

        @NotNull
        @Positive
        Long labelId
) { }
