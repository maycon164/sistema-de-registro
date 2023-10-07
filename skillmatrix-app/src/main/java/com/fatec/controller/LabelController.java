package com.fatec.controller;

import com.fatec.dto.UpdateLabelDTO;
import com.fatec.model.Label;
import com.fatec.service.LabelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/labels")
@RequiredArgsConstructor
@Tag(name = "label")
public class LabelController {

    private final LabelService labelService;

    @GetMapping
    public List<Label> getLabels(){
        return labelService.getLabels();
    }

    @PutMapping("/{labelId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEAM_LEADER', 'COLLABORATOR')")
    public ResponseEntity updteLabel(@PathVariable(name = "labelId") Long labelId, @Valid @RequestBody UpdateLabelDTO updateLabel){
        labelService.updateLabel(labelId, updateLabel);
        return ResponseEntity.accepted().build();
    }

}
