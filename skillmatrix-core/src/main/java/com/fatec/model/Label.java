package com.fatec.model;

import com.fatec.model.enums.LabelEnum;

import java.util.List;

public record Label(Long id, LabelEnum label, List<Skill> skills) {
}
