package com.fatec.model;

public record AuthResponseDTO(
        User user,
        String jwt
) { }
