package com.fatec.controller;

import com.fatec.dto.UpdateLabelDTO;
import com.fatec.model.Label;
import com.fatec.service.LabelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @GetMapping
    public List<Label> getLabels(){
        return labelService.getLabels();
    }

    @PutMapping("/{labelId}")
    public ResponseEntity updteLabel(@PathVariable(name = "labelId") Long labelId, @Valid @RequestBody UpdateLabelDTO updateLabel){
        labelService.updateLabel(labelId, updateLabel);
        return ResponseEntity.accepted().build();
    }

}
