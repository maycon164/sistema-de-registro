package com.fatec.controller;

import com.fatec.model.SkillModel;
import com.fatec.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("skills")
@RequiredArgsConstructor
public class SkillController {

    public final SkillService skillService;

    @GetMapping()
    public ResponseEntity<List<SkillModel>> getAllSkills(){
        return ResponseEntity.ok(skillService.getAllSkills());
    }
}
