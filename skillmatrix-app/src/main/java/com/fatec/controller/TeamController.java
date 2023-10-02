package com.fatec.controller;

import com.fatec.model.Team;
import com.fatec.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'TEAM_LEADER')")
    public ResponseEntity<List<Team>> getTeams(){
        List<Team> teams = teamService.getAllTeams();

        if(teams.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(teams);
    }

}
