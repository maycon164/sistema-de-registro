package com.fatec.dto;

import com.fatec.model.enums.LabelEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record GetUsersDTO(
    String search,
    @NotNull
    @Positive
    Integer pageNumber,
    Boolean isAdmin,
    Boolean active,
    List<SkillFilterDTO> skills,
    List<LabelEnum> labels
) { }

