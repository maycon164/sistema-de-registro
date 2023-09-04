package com.fatec.dto;

import com.fatec.model.Skill;
import lombok.Builder;

import java.util.List;

@Builder
public record LabelSkillResponse(
        Long id,
        String label,
        List<Skill> skills
) {
}
