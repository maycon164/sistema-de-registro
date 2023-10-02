package com.fatec.controller;

import com.fatec.dataprovider.entities.UserEntity;
import com.fatec.dto.GetUsersDTO;
import com.fatec.dto.UserDTO;
import com.fatec.model.paginated.PaginatedUserResult;
import com.fatec.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UsersController {

    public final UserService userService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'TEAM_LEADER')")
    public ResponseEntity<PaginatedUserResult> getAllUsers(@Valid GetUsersDTO getUsersDTO){
        return ResponseEntity.ok(userService.getAllUsers(getUsersDTO));
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'TEAM_LEADER')")
    public ResponseEntity<UserEntity> insertNewUser(@Valid @RequestBody UserDTO createUserDTO){
        return ResponseEntity.ok(userService.insertNewUser(createUserDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEAM_LEADER')")
    public ResponseEntity<UserEntity> updateUser(@PathVariable(value="id") Long id, @Valid @RequestBody UserDTO updateUserDTO){
        System.out.println(updateUserDTO);
        return ResponseEntity.ok(userService.updateUser(id, updateUserDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEAM_LEADER')")
    public ResponseEntity<String> deleteUser(@PathVariable(value="id") Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
