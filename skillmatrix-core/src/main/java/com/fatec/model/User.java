package com.fatec.model;

import com.fatec.model.enums.LabelEnum;
import com.fatec.model.enums.RoleEnum;
import lombok.Builder;

@Builder
public record User(
        Long id,
        String name,
        String email,
        Boolean isActive,
        Label label,
        RoleEnum role,
        String picture
) {
    public User withPicture(String picture){
        return new User(id(), name(), email(), isActive(), label(), role(), picture);
    }
}
