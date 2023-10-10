package com.fatec.model;

import com.fatec.dto.AnswerDTO;
import com.fatec.model.enums.LabelEnum;
import com.fatec.model.enums.LevelEnum;
import com.fatec.model.enums.RoleEnum;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record User(
        Long id,
        String name,
        String email,
        Boolean isActive,
        Label label,
        RoleEnum role,
        String picture,
        List<Snapshot> snapshots

) {
    public User withPicture(String picture){
        return new User(id(), name(), email(), isActive(), label(), role(), picture, null);
    }
}
