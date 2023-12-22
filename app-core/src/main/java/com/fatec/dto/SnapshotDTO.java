package com.fatec.dto;

import java.util.List;

public record SnapshotDTO (
        List<AnswerDTO> answers
) { }

