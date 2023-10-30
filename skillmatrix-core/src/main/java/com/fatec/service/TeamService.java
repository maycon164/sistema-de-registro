package com.fatec.service;

import com.fatec.dataprovider.entities.TeamEntity;
import com.fatec.dataprovider.entities.UserEntity;
import com.fatec.dataprovider.repository.TeamRepository;
import com.fatec.dataprovider.specification.TeamSpecification;
import com.fatec.dto.GetTeamsDTO;
import com.fatec.model.Team;
import com.fatec.model.User;
import com.fatec.model.paginated.PaginatedTeamResult;
import com.fatec.model.paginated.PaginatedUserResult;
import com.fatec.utils.PaginationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public PaginatedTeamResult getAllTeams(GetTeamsDTO getTeamsDTO){
        Specification<TeamEntity> spec = TeamSpecification.buildSpecification(getTeamsDTO);
        PageRequest pageRequest = PaginationUtils.getPageRequest(getTeamsDTO.pageNumber());
        Page<Team> pageableUsers = teamRepository.findAll(spec, pageRequest).map(this::toTeamModel);

        return PaginatedTeamResult.builder()
                .teams(pageableUsers.getContent())
                .totalPages(pageableUsers.getTotalPages())
                .build();
    }

    public List<Team> getAllTeamsSelect() {
        return teamRepository.findAll().stream().map(this::toTeamModel).toList();
    }

    private Team toTeamModel(TeamEntity team){
        return new Team(team.getId(), team.getName());
    }
}
