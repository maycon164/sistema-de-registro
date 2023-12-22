package com.fatec.model;

import lombok.Builder;

import java.util.List;

@Builder
public record Team (
        Long id,
        String name,
        String description,
        boolean active,
        List<User> members,

        User leader
) { }
