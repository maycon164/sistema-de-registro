package com.fatec.model.paginated;

import com.fatec.model.Team;
import com.fatec.model.User;
import lombok.Builder;

import java.util.List;

@Builder
public record PaginatedTeamResult(
        List<Team> teams,
        Integer page,
        Integer totalPages
){}
