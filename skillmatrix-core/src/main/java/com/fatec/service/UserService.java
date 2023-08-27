package com.fatec.service;

import com.fatec.dataprovider.entities.UserEntity;
import com.fatec.dataprovider.repository.UserRepository;
import com.fatec.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity insertNewUser(UserDTO createUserDTO){
        log.warn("User Created in internal Database");
        return userRepository.save(toUserEntity(createUserDTO));
    }

    private UserEntity toUserEntity(UserDTO userToCreate){
        return UserEntity.builder()
                .name(userToCreate.name())
                .email(userToCreate.email())
                .build();
    }
}
