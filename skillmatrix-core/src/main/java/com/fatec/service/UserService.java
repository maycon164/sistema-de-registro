package com.fatec.service;

import com.fatec.dataprovider.entities.LabelEntity;
import com.fatec.dataprovider.entities.UserEntity;
import com.fatec.dataprovider.repository.UserRepository;
import com.fatec.dataprovider.specification.UserSpecifications;
import com.fatec.dto.GetUsersDTO;
import com.fatec.dto.UserDTO;
import com.fatec.exceptions.NotFound;
import com.fatec.model.Label;
import com.fatec.model.User;
import com.fatec.model.enums.RoleEnum;
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

    public User findByEmail(String email){
        var userEntity = userRepository.findByEmail(email);
        if(userEntity == null) throw new NotFound();
        return toUserModel(userEntity);
    }

    public UserEntity insertNewUser(UserDTO createUserDTO){
        log.info("User Created in internal Database");
        return userRepository.save(toUserEntity(createUserDTO));
    }

    public UserEntity updateUser(Long id, UserDTO updateUserDTO) {
        var user = userRepository.findById(id).orElseThrow(NotFound::new);

        user.setName(updateUserDTO.name());
        user.setEmail(updateUserDTO.email());
        user.setLabel(new LabelEntity(updateUserDTO.labelId(), null));

        return userRepository.save(user);
    }

    public String deleteUser(Long id){
        UserEntity user = userRepository.findById(id).orElseThrow(NotFound::new);
        if(user.getIsActive()){
            user.setIsActive(false);
            userRepository.save(user);
            return "User has been deactivated";
        }

        userRepository.delete(user);
        return "User has been deleted";
    }

    private User toUserModel(UserEntity user){
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .label(new Label(user.getLabel().getId(), user.getLabel().getLabel(), null))
                .isActive(user.getIsActive())
                .build();
    }
    private UserEntity toUserEntity(UserDTO userDTO){
        return UserEntity.builder()
                .name(userDTO.name())
                .email(userDTO.email())
                .isActive(true)
                .label(new LabelEntity(userDTO.labelId(), null))
                .role(userDTO.role())
                .build();
    }
}
