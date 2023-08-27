package com.fatec.controller;

import com.fatec.dto.GetSkillsDTO;
import com.fatec.model.paginated.PaginatedSkillResult;
import com.fatec.service.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @GetMapping()
    public ResponseEntity<PaginatedSkillResult> getAllSkills(@Valid GetSkillsDTO getSkillsDTO) {
        return ResponseEntity.ok(skillService.getAllSkills(getSkillsDTO));
    }

}
