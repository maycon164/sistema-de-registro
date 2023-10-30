package com.fatec.controller;

import com.fatec.dto.GetTeamsDTO;
import com.fatec.model.Team;
import com.fatec.model.paginated.PaginatedTeamResult;
import com.fatec.service.TeamService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'TEAM_LEADER')")
    public ResponseEntity<PaginatedTeamResult> getTeams(@Valid GetTeamsDTO getTeamsDTO){
        PaginatedTeamResult teams = teamService.getAllTeams(getTeamsDTO);

        if(teams.teams().isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(teams);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Team>> getTeams(){
        List<Team> teams = teamService.getAllTeamsSelect();
        if(teams.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(teams);
    }

}
