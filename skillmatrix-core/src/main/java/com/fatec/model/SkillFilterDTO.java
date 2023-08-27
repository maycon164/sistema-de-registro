package com.fatec.model;

import com.fatec.model.enums.LevelEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SkillFilterDTO(
        @NotNull
        @Positive
        Integer skillId,
        @NotNull
        LevelEnum level
){ }