package com.fatec.model;

import com.fatec.dto.AnswerDTO;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record Snapshot(
        Long snapshotId,
        Date createdAt,
        List<AnswerDTO> answers
) { }
