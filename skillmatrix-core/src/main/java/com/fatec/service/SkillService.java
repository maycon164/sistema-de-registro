package com.fatec.service;

import com.fatec.dataprovider.entities.LabelEntity;
import com.fatec.dataprovider.entities.SkillEntity;
import com.fatec.dataprovider.repository.LabelRepository;
import com.fatec.dataprovider.repository.SkillRepository;
import com.fatec.dataprovider.specification.SkillsSpecifications;
import com.fatec.dto.GetSkillsDTO;
import com.fatec.dto.SkillDTO;
import com.fatec.model.Label;
import com.fatec.model.paginated.PaginatedSkillResult;
import com.fatec.model.Skill;
import com.fatec.exceptions.NotFound;
import com.fatec.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final LabelRepository labelRepository;

    public PaginatedSkillResult getAllSkills(GetSkillsDTO getRequestSkillsDTO){
        Specification<SkillEntity> spec = new SkillsSpecifications().buildSpecifications(getRequestSkillsDTO);
        PageRequest pageRequest = PaginationUtils.getPageRequest(getRequestSkillsDTO.pageNumber());

        Page<Skill> pageableSkills = skillRepository.findAll(spec, pageRequest).map(this::toSkillModel);

        return PaginatedSkillResult.builder()
                .skills(pageableSkills.getContent())
                .totalPages(pageableSkills.getTotalPages())
                .build();
    }

    public Skill createNewSkill(SkillDTO skillDTO){
        SkillEntity skillCreated = skillRepository.save(toSkillEntity(skillDTO));
        return toSkillModel(skillCreated);
    }

    public Skill updateSkill(Long id, SkillDTO skillDTO){
        SkillEntity skill = skillRepository.findById(id).orElseThrow(NotFound::new);
        LabelEntity label = labelRepository.findById(skillDTO.labelId()).orElseThrow(NotFound::new);

        skill.setDescription(skillDTO.description());
        skill.setName(skillDTO.name());
        skill.setLabel(label);

        skillRepository.save(skill);

        return toSkillModel(skill);
    }

    public String deactivateSkill(Long id) {
        SkillEntity skill = skillRepository.findById(id).orElseThrow(NotFound::new);

        if(skill.getActive()){
            skill.setActive(false);
            skillRepository.save(skill);
            return "Skill Deactivated";
        }

        skillRepository.delete(skill);
        return "Skill Deleted";
    }

    private Skill toSkillModel(SkillEntity skillEntity){
        return Skill.builder()
                .id(skillEntity.getId())
                .name(skillEntity.getName())
                .active(skillEntity.getActive())
                .label(new Label(skillEntity.getLabel().getId(), skillEntity.getLabel().getLabel(), null))
                .description(skillEntity.getDescription())
                .build();
    }

    private SkillEntity toSkillEntity(SkillDTO skillDTO){
        return SkillEntity.builder()
                .name(skillDTO.name())
                .description(skillDTO.description())
                .label(new LabelEntity(skillDTO.labelId(), null))
                .build();
    }
}
