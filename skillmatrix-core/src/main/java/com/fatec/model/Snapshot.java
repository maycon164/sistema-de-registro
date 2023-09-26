package com.fatec.model;

import com.fatec.dto.AnswerDTO;

import java.util.Date;
import java.util.List;

public record Snapshot(
        Long id,
        Date createdAt,
        List<AnswerDTO> answers
) { }
