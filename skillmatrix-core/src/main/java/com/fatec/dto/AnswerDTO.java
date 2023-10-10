package com.fatec.dto;

import com.fatec.model.enums.LevelEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record AnswerDTO(
        String labelEnum,
        @Min(0) @Max(5)
        Long rating,
        @Positive
        Long skillId,
        String skillName,
        boolean willingToAnswerQuestions,
        boolean willingToPresent,
        boolean workedWithTech,
        LevelEnum level
) { }
