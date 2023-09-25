package com.fatec.controller;

import com.fatec.dto.SnapshotDTO;
import com.fatec.service.SnapshotService;
import com.fatec.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("snapshot")
@RequiredArgsConstructor
public class SnapshotController {

    private final SnapshotService snapshotService;

    @PostMapping
    public ResponseEntity saveNewSnapshot(@Valid @RequestBody SnapshotDTO snapshot){
        snapshotService.saveSnapshot(snapshot);
        return ResponseEntity.ok().build();
    }
}
