package com.fatec.model;

import lombok.Builder;

@Builder
public record Skill(
        Long id,
        String name,
        String description,
        Label label
) {
}
