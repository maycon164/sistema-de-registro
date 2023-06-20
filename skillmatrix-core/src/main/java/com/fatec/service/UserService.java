package com.fatec.service;

import com.fatec.adapter.out.entities.UserEntity;
import com.fatec.adapter.out.repository.UserRepository;
import com.fatec.dto.CreateUserDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity insertNewUser(CreateUserDTO createUserDTO){
        return userRepository.save(toUserEntity(createUserDTO));
    }

    private UserEntity toUserEntity(CreateUserDTO userToCreate){
        return UserEntity.builder()
                .name(userToCreate.name())
                .email(userToCreate.email())
                .build();
    }
}
