package com.fatec.adapter.out.dao;

import com.fatec.model.LabelsEnum;
import com.fatec.model.SkillModel;

import java.util.List;

public interface SkillDaoPort {

    public List<SkillModel> getAll();
    public List<SkillModel> getAll(LabelsEnum label);

}
