package com.fatec.adapter.out.dao;

import com.fatec.adapter.out.entities.SkillEntity;
import com.fatec.adapter.out.repository.SkillRepository;
import com.fatec.model.SkillModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SkillDaoPostgresAdapter implements SkillDaoPort{

    private final SkillRepository skillRepository;

    @Override
    public List<SkillModel> getAll() {
        return skillRepository.findAll().stream().map(this::toSkillModel).toList();
    }

    private SkillModel toSkillModel(SkillEntity skill){
        return new SkillModel(
                skill.getId(),
                skill.getName(),
                skill.getDescription(),
                null
        );
    }
}
