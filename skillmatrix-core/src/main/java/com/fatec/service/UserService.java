package com.fatec.service;

import com.fatec.dataprovider.entities.UserEntity;
import com.fatec.dataprovider.repository.UserRepository;
import com.fatec.dataprovider.specification.UserSpecifications;
import com.fatec.dto.GetUsersDTO;
import com.fatec.dto.UserDTO;
import com.fatec.model.User;
import com.fatec.model.paginated.PaginatedUserResult;
import com.fatec.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    public PaginatedUserResult getAllUsers(GetUsersDTO getUsersDTO){

        Specification<UserEntity> spec = new UserSpecifications().buildSpecification(getUsersDTO);
        PageRequest pageRequest = PaginationUtils.getPageRequest(getUsersDTO.pageNumber());
        Page<User> pageableUsers = userRepository.findAll(spec, pageRequest).map(this::toUserModel);

        return PaginatedUserResult.builder()
                .users(pageableUsers.getContent())
                .totalPages(pageableUsers.getTotalPages())
                .build();
    }

    public UserEntity insertNewUser(UserDTO createUserDTO){
        log.info("User Created in internal Database");
        return userRepository.save(toUserEntity(createUserDTO));
    }

    private User toUserModel(UserEntity user){
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .isActive(user.getIsActive())
                .build();
    }
    private UserEntity toUserEntity(UserDTO userToCreate){
        return UserEntity.builder()
                .name(userToCreate.name())
                .email(userToCreate.email())
                .build();
    }
}
