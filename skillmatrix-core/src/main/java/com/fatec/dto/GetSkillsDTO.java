package com.fatec.dto;

import com.fatec.model.LabelEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record GetSkillsDTO(
        String search,
        Boolean active,
        String category,
        @NotNull
    @Positive
    Integer pageNumber,
        Long roleId,

        List<LabelEnum> labels
) { }
