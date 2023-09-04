package com.fatec.dto;

import com.fatec.model.Label;

public record LabelUserInfo(
    Label label,
    Integer QtdUsers
) { }
