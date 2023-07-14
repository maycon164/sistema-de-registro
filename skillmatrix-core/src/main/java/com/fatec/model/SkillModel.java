package com.fatec.model;

public record SkillModel(
        Long id,
        String name,
        String description,
        LabelModel label
) {
}
