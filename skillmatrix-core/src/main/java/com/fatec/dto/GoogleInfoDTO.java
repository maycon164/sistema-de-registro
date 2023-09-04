package com.fatec.dto;

public record GoogleInfoDTO(
        String id,
        String email,
        Boolean verified_email,
        String name,
        String given_name,
        String family_name,
        String picture,
        String locale
) {
}
