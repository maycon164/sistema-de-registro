package com.fatec.service;

import com.fatec.adapter.out.dao.SkillDaoPort;
import com.fatec.model.SkillModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SkillService {

    private final SkillDaoPort skillAdapter;

    public List<SkillModel> getAllSkills(){
        return skillAdapter.getAll();
    }

}
