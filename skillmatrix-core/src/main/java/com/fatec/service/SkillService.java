package com.fatec.service;

import com.fatec.dataprovider.entities.SkillEntity;
import com.fatec.dataprovider.repository.SkillRepository;
import com.fatec.dataprovider.specification.SkillsSpecifications;
import com.fatec.dto.GetSkillsDTO;
import com.fatec.model.PaginatedSkillResult;
import com.fatec.model.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final Integer PAGE_SIZE = 10;

    private final SkillRepository skillRepository;

    public PaginatedSkillResult getAllSkills(GetSkillsDTO getRequestSkillsDTO){
        Specification<SkillEntity> spec = new SkillsSpecifications().buildSpecifications(getRequestSkillsDTO);
        PageRequest pageRequest = PageRequest.of(getPageNumber(getRequestSkillsDTO.pageNumber()),PAGE_SIZE);

        Page<Skill> pageableSkills = skillRepository.findAll(spec, pageRequest).map(this::toSkillModel);

        return PaginatedSkillResult.builder()
                .skills(pageableSkills.getContent())
                .totalPages(pageableSkills.getTotalPages())
                .build();
    }

    private Skill toSkillModel(SkillEntity skillEntity){
        return Skill.builder()
                .id(skillEntity.getId())
                .name(skillEntity.getName())
                .description(skillEntity.getDescription())
                .build();
    }

    private Integer getPageNumber(Integer pageNumber){
        return pageNumber - 1;
    }

}
