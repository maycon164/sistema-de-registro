package com.fatec.dto;

import com.fatec.model.User;

public record AuthResponseDTO(
        User user,
        String jwt
) { }
