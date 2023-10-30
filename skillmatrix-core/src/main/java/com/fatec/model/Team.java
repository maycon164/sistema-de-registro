package com.fatec.model;

import lombok.Builder;

@Builder
public record Team (Long id, String name, String description, boolean active) { }
