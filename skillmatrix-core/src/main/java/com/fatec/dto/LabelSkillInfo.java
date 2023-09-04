package com.fatec.dto;

import com.fatec.model.enums.LabelEnum;

public record LabelSkillInfo(
        LabelEnum label,
        Integer qtdSkills
) {}
