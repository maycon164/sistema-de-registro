package com.fatec.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GetTeamsDTO (
    String search,
    @NotNull
    @Positive
            Integer pageNumber
){}
