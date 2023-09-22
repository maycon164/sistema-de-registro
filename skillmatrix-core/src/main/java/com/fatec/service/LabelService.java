package com.fatec.service;

import com.fatec.dataprovider.entities.LabelEntity;
import com.fatec.dataprovider.entities.SkillEntity;
import com.fatec.dataprovider.repository.LabelRepository;
import com.fatec.dataprovider.repository.SkillRepository;
import com.fatec.dto.*;
import com.fatec.model.Label;
import com.fatec.model.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;
    private final SkillRepository skillRepository;

    public List<Label> getLabels(){
        List<LabelEntity> labels = labelRepository.findAll();
        List<Label> result = new ArrayList<>();

        for(LabelEntity label: labels){
            List<SkillEntity> skills = skillRepository.findAllByLabelId(label.getId());
            result.add(new Label(label.getId(), label.getLabel(), skills.stream().map(skill -> Skill.builder()
                    .id(skill.getId())
                    .name(skill.getName())
                    .description(skill.getDescription())
                    .labelValue(skill.getLabel().getLabel())
                    .build()).toList()));
        }

        return result;
    }

    public void updateLabel(Long labelId, UpdateLabelDTO updateLabelDTO ) {
        updateLabelDTO.skills().forEach(skillId -> {
            SkillEntity skillEntity = skillRepository.findById(skillId).get();
            skillEntity.setLabel(new LabelEntity(labelId, null));
            skillRepository.save(skillEntity);
        });
    }

    private Label toLabelModel(LabelEntity label){
        return new Label(
                label.getId(),
                label.getLabel(),
                null
        );
    }


}