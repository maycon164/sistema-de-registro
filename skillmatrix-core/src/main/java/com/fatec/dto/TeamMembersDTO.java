package com.fatec.dto;

import java.util.List;

public record TeamMembersDTO(
        List<Long> membersIds
) { }
