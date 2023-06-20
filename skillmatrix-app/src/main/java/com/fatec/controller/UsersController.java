package com.fatec.controller;

import com.fatec.adapter.out.entities.UserEntity;
import com.fatec.dto.CreateUserDTO;
import com.fatec.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UsersController {

    public final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping()
    public ResponseEntity<UserEntity> insertNewUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        return ResponseEntity.ok(userService.insertNewUser(createUserDTO));
    }

}
