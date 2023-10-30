package com.fatec.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatec.exceptions.SkillFilterException;
import com.fatec.model.enums.LabelEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public record GetUsersDTO(
    String search,
    @NotNull
    @Positive
    Integer pageNumber,
    Boolean isAdmin,
    Boolean active,
    String skills,
    List<LabelEnum> labels,

    String jobPosition,
    Long team
) {
    public List<SkillFilterDTO> getSkillFilter() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if(Objects.isNull(this.skills())) return List.of();
            return objectMapper.readValue(this.skills(), new TypeReference<List<SkillFilterDTO>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            //TODO map as 400 bad request exception handler
            throw new SkillFilterException();
        }
    }

}

