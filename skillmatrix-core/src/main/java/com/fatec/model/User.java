package com.fatec.model;

import com.fatec.model.enums.LabelEnum;
import lombok.Builder;

@Builder
public record User(
        Long id,
        String name,
        String email,
        Boolean isActive,
        LabelEnum label
) { }
