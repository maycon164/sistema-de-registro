package com.fatec.model;

import com.fatec.model.enums.JobPositionEnum;
import com.fatec.model.enums.RoleEnum;
import lombok.Builder;

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
        String jobPosition,
        List<Snapshot> snapshots,
        Team team

) {
    public User withPicture(String picture){
        return new User(id(), name(), email(), isActive(), label(), role(), picture, jobPosition(), null, null);
    }
}
