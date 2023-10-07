package com.fatec.controller;

import com.fatec.dto.GetSkillsDTO;
import com.fatec.dto.SkillDTO;
import com.fatec.model.Skill;
import com.fatec.model.paginated.PaginatedSkillResult;
import com.fatec.service.SkillService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("skills")
@RequiredArgsConstructor
@Tag(name = "skills")
public class SkillController {

    private final SkillService skillService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'TEAM_LEADER', 'COLLABORATOR')")
    public ResponseEntity<PaginatedSkillResult> getAllSkills(@Valid GetSkillsDTO getSkillsDTO) {
        return ResponseEntity.ok(skillService.getAllSkills(getSkillsDTO));
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'TEAM_LEADER')")
    public ResponseEntity<Skill> createNewSkill(@RequestBody @Valid SkillDTO skillDTO){
        return ResponseEntity.ok(skillService.createNewSkill(skillDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEAM_LEADER')")
    public ResponseEntity<Skill> updateSkill(@PathVariable(value="id") Long id, @RequestBody @Valid SkillDTO skillDTO){
        return ResponseEntity.ok(skillService.updateSkill(id, skillDTO));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEAM_LEADER')")
    public ResponseEntity<String> deactivateSkill(@PathVariable(value="id") Long id){
        return ResponseEntity.ok(skillService.deactivateSkill(id));
    }

}

