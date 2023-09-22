package com.fatec.model;

import com.fatec.model.enums.LabelEnum;
import lombok.Builder;

@Builder
public record Skill(
        Long id,
        String name,
        String description,
        Label label,
        LabelEnum labelValue,
        Boolean active
) {
}
