package com.fatec.dto;

import jakarta.validation.constraints.NotNull;

public record TeamDTO(
        @NotNull
        String name,
        @NotNull
        String description,

        @NotNull
        Long teamLeaderId,

        boolean active
) {
}
