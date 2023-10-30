package com.fatec.service;

import com.fatec.dataprovider.entities.TeamEntity;
import com.fatec.dataprovider.entities.UserEntity;
import com.fatec.dataprovider.repository.TeamRepository;
import com.fatec.dataprovider.repository.UserRepository;
import com.fatec.dataprovider.specification.TeamSpecification;
import com.fatec.dto.GetTeamsDTO;
import com.fatec.dto.TeamDTO;
import com.fatec.dto.TeamMembersDTO;
import com.fatec.exceptions.NotFound;
import com.fatec.model.Team;
import com.fatec.model.User;
import com.fatec.model.enums.RoleEnum;
import com.fatec.model.paginated.PaginatedTeamResult;
import com.fatec.model.paginated.PaginatedUserResult;
import com.fatec.utils.PaginationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public PaginatedTeamResult getAllTeams(GetTeamsDTO getTeamsDTO){
        Specification<TeamEntity> spec = TeamSpecification.buildSpecification(getTeamsDTO);
        PageRequest pageRequest = PaginationUtils.getPageRequest(getTeamsDTO.pageNumber());
        Page<Team> pageableUsers = teamRepository.findAll(spec, pageRequest).map(this::toTeamModel);

        return PaginatedTeamResult.builder()
                .teams(pageableUsers.getContent())
                .totalPages(pageableUsers.getTotalPages())
                .build();
    }

    public List<User> getAllTeamLeaders () {
        return userRepository.findAllTeamLeaders()
                .stream().map(userEntity -> User.builder()
                        .id(userEntity.getId())
                        .name(userEntity.getName())
                        .build())
                .toList();
    }

    public List<Team> getAllTeamsSelect() {
        return teamRepository.findAll().stream().map(this::toTeamModel).toList();
    }

    public Team addTeam(TeamDTO teamDTO){
        return toTeamModel(teamRepository.save(toTeamEntity(teamDTO)));
    }

    public String deactivateTeam(Long id) {
        TeamEntity team = teamRepository.findById(id).orElseThrow(() -> new NotFound("Team not found"));

        if(team.getIsActive()) {
            team.setIsActive(false);
            teamRepository.save(team);
            return "Team has been deactivated";
        }

        teamRepository.delete(team);

        return "Team has been deleted";
    }

    public String updateTeam(Long id, TeamDTO teamDTO){
        TeamEntity team = teamRepository.findById(id).orElseThrow(()-> new NotFound("Team not found"));
        TeamEntity teamUpdated = toTeamEntity(teamDTO);
        teamUpdated.setId(id);
        teamRepository.save(teamUpdated);
        return "Team Updated";
    }

    public Team updateTeamMembers(Long id, TeamMembersDTO teamDTO){
        TeamEntity teamEntity = teamRepository.findById(id).orElseThrow(() -> new NotFound("Team not found"));

        List<UserEntity> members = teamDTO.membersIds().stream()
                .map(userId -> UserEntity.builder().id(userId).build())
                .toList();

        teamEntity.getMembers().clear();
        teamEntity.getMembers().addAll(members);
        teamRepository.save(teamEntity);
        return toTeamModel(teamEntity);
    }

    public Team getTeamInfo(Long id) {
        return toTeamModel(teamRepository.findById(id).orElseThrow(() -> new NotFound("Team not found!!")));
    }

    private Team toTeamModel(TeamEntity team){

        User leader =null;
        if(team.getLeader() != null){
            leader = User.builder()
                    .id(team.getLeader().getId())
                    .email(team.getLeader().getEmail())
                    .name(team.getLeader().getName()).build();
        }

        List<User> members = new ArrayList<>();
        if(team.getMembers() != null) {
            members.addAll(team.getMembers().stream().map(user -> User
                    .builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .jobPosition(user.getJobPosition())
                    .build()
            ).toList());
        }

        return new Team(
                team.getId(),
                team.getName(),
                team.getDescription(),
                team.getIsActive(),
                members,
                leader
        );
    }

    private TeamEntity toTeamEntity(TeamDTO teamDTO){

        UserEntity userEntity = null;
        if(teamDTO.teamLeaderId() != null){
            userEntity = UserEntity.builder().id(teamDTO.teamLeaderId()).build();
        }

        return TeamEntity.builder()
                .name(teamDTO.name())
                .description(teamDTO.description())
                .isActive(teamDTO.active())
                .leader(userEntity)
                .build();
    }
}
