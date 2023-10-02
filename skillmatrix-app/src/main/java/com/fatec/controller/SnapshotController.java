package com.fatec.controller;

import com.fatec.dataprovider.entities.UserEntity;
import com.fatec.dto.SnapshotDTO;
import com.fatec.model.Snapshot;
import com.fatec.model.User;
import com.fatec.service.SnapshotService;
import com.fatec.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("snapshot")
@RequiredArgsConstructor
public class SnapshotController {

    private final SnapshotService snapshotService;

    @PostMapping
    @PreAuthorize("hasAnyRole('COLLABORATOR', 'TEAM_LEADER', 'ADMIN')")
    public ResponseEntity saveNewSnapshot(@Valid @RequestBody SnapshotDTO snapshot){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        snapshotService.saveSnapshot(user, snapshot);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'TEAM_LEADER', 'COLLABORATOR')")
    public ResponseEntity<Snapshot> getUserSnapshot(@RequestParam("last") String last){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!last.isEmpty()) {
            return ResponseEntity.ok(snapshotService.getSnapshot(user));
        }

        return ResponseEntity.noContent().build();
    }
}
