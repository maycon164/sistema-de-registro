package com.fatec.service;

import com.fatec.dataprovider.entities.LabelEntity;
import com.fatec.dataprovider.entities.SnapshotEntity;
import com.fatec.dataprovider.entities.TeamEntity;
import com.fatec.dataprovider.entities.UserEntity;
import com.fatec.dataprovider.repository.TeamRepository;
import com.fatec.dataprovider.repository.UserRepository;
import com.fatec.dataprovider.specification.UserSpecifications;
import com.fatec.dto.AnswerDTO;
import com.fatec.dto.GetUsersDTO;
import com.fatec.dto.UserDTO;
import com.fatec.exceptions.NotFound;
import com.fatec.exceptions.UserNotFound;
import com.fatec.model.Label;
import com.fatec.model.Snapshot;
import com.fatec.model.Team;
import com.fatec.model.User;
import com.fatec.model.enums.JobPositionEnum;
import com.fatec.model.enums.LevelEnum;
import com.fatec.model.paginated.PaginatedUserResult;
import com.fatec.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Level;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserSpecifications userSpecifications;
    private final TeamRepository teamRepository;
    public PaginatedUserResult getAllUsers(GetUsersDTO getUsersDTO){
        Specification<UserEntity> spec = userSpecifications.buildSpecification(getUsersDTO);
        PageRequest pageRequest = PaginationUtils.getPageRequest(getUsersDTO.pageNumber());
        Page<User> pageableUsers = userRepository.findAll(spec, pageRequest).map(this::toUserModel);

        return PaginatedUserResult.builder()
                .users(pageableUsers.getContent())
                .totalPages(pageableUsers.getTotalPages())
                .build();
    }

    public User findByEmail(String email){
        var userEntity = userRepository.findByEmail(email);
        if(userEntity == null) throw new UserNotFound();
        return toUserModel(userEntity);
    }

    public User getUserInfo(Long userId){
        return toUserModel(userRepository.findById(userId).orElseThrow(UserNotFound::new));
    }

    public UserEntity insertNewUser(UserDTO createUserDTO){
        log.info("User Created in internal Database");

        return userRepository.save(toUserEntity(createUserDTO));
    }

    public UserEntity updateUser(Long id, UserDTO updateUserDTO) {
        var user = userRepository.findById(id).orElseThrow(UserNotFound::new);

        user.setName(updateUserDTO.name());
        user.setEmail(updateUserDTO.email());
        user.setJobPosition(updateUserDTO.jobPosition());

        TeamEntity teamEntity = teamRepository.findById(updateUserDTO.teamId()).orElseThrow(() -> new NotFound("Team not found!"));
        user.setTeam(teamEntity);

        return userRepository.save(user);
    }

    public String deleteUser(Long id){
        UserEntity user = userRepository.findById(id).orElseThrow(UserNotFound::new);
        if(user.getIsActive()){
            user.setIsActive(false);
            userRepository.save(user);
            return "User has been deactivated";
        }

        userRepository.delete(user);
        return "User has been deleted";
    }

    private User toUserModel(UserEntity user){
        Team team = null;

        if(user.getTeam() != null) {
            team = Team.builder()
                    .id(user.getTeam().getId())
                    .name(user.getTeam().getName())
                    .build();
        }


        List<SnapshotEntity> snapshotEntities= new ArrayList<>(user.getSnapshots());

        snapshotEntities.sort((s1, s2) -> s2.getCreatedAt().compareTo(s1.getCreatedAt()));

        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .jobPosition(user.getJobPosition())
                .snapshots(snapshotEntities.stream().map(this::toSnapshot).toList())
                .team(team)
                .isActive(user.getIsActive())
                .build();
    }
    private Snapshot toSnapshot(SnapshotEntity snapshotEntity) {

        return Snapshot.builder()
                .snapshotId(snapshotEntity.getId())
                .answers(snapshotEntity.getAnswers().stream().map(snapshotAnswerEntity -> AnswerDTO.builder()
                                .skillId(snapshotAnswerEntity.getSkill().getId())
                                .skillName(snapshotAnswerEntity.getSkill().getName())
                                .labelEnum(snapshotAnswerEntity.getSkill().getLabel().getLabel().toString())
                                .rating(snapshotAnswerEntity.getRating())
                                .willingToPresent(snapshotAnswerEntity.getWillingToPresent())
                                .workedWithTech(snapshotAnswerEntity.getWorkedWithTech())
                                .willingToAnswerQuestions(snapshotAnswerEntity.getWillingToAnswerQuestions())
                                .level(parseLevelEnum(snapshotAnswerEntity.getRating()))
                                .build())
                        .toList()
                )
                .createdAt(snapshotEntity.getCreatedAt())
                .build();
    }

    private LevelEnum parseLevelEnum(Long rating) {
        if(rating > 0 && rating <= 2) return LevelEnum.LOW;
        if(rating > 2 && rating <= 4) return LevelEnum.MED;
        return LevelEnum.ADV;
    }

    private UserEntity toUserEntity(UserDTO userDTO){

        TeamEntity teamEntity = teamRepository.findById(userDTO.teamId()).orElseThrow(() -> new NotFound("Team not found"));

        return UserEntity.builder()
                .name(userDTO.name())
                .email(userDTO.email())
                .isActive(true)
                .team(teamEntity)
                .jobPosition(userDTO.jobPosition())
                .role(userDTO.role())
                .build();
    }
}
