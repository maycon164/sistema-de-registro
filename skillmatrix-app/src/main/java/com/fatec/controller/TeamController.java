package com.fatec.controller;

import com.fatec.dto.GetTeamsDTO;
import com.fatec.dto.TeamDTO;
import com.fatec.dto.TeamMembersDTO;
import com.fatec.model.Team;
import com.fatec.model.User;
import com.fatec.model.paginated.PaginatedTeamResult;
import com.fatec.service.TeamService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/leaders")
    public ResponseEntity<List<User>> getTeamLeaders(){
        return ResponseEntity.ok(teamService.getAllTeamLeaders());
    }

    @GetMapping("{id}")
    public ResponseEntity<Team> getTeamInfo(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(teamService.getTeamInfo(id));
    }
    @PostMapping
    public ResponseEntity<Team> addNewTeam(@Valid @RequestBody TeamDTO teamDTO){
        return ResponseEntity.ok(teamService.addTeam(teamDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateTeam(@PathVariable(value = "id") Long id, @Valid @RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.updateTeam(id, teamDTO));
    }

    @PutMapping("{id}/members")
    public ResponseEntity<Team> updateTeamMembers(@PathVariable(value = "id") Long id, @Valid @RequestBody TeamMembersDTO teamDTO) {
        return ResponseEntity.ok(teamService.updateTeamMembers(id, teamDTO));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(teamService.deactivateTeam(id));
    }
}
