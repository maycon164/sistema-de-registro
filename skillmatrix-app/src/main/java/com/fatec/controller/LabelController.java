package com.fatec.controller;

import com.fatec.dto.LabelSkillResponse;
import com.fatec.model.Label;
import com.fatec.model.Skill;
import com.fatec.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
