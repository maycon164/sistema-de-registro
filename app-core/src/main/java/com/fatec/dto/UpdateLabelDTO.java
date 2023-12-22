package com.fatec.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateLabelDTO(
        @NotNull
        @NotEmpty
        List<Long> skills
) {}
