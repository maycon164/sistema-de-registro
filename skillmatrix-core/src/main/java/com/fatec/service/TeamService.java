package com.fatec.service;

import com.fatec.dataprovider.entities.TeamEntity;
import com.fatec.dataprovider.repository.TeamRepository;
import com.fatec.model.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public List<Team> getAllTeams(){
        return teamRepository.findAll().stream().map(this::toTeamModel).toList();
    }

    private Team toTeamModel(TeamEntity team){
        return new Team(team.getId(), team.getName());
    }
}
