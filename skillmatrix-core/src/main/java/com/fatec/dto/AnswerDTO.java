package com.fatec.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record AnswerDTO(
        String labelEnum,
        @Min(0) @Max(5)
        Long rating,
        @Positive
        Long skillId,
        String skillName,
        boolean willingToAnswerQuestions,
        boolean willingToPresent,
        boolean workedWithTech
) {
}
