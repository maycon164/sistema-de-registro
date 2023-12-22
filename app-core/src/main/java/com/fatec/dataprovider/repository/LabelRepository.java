package com.fatec.dataprovider.repository;

import com.fatec.dataprovider.entities.LabelEntity;
import com.fatec.dto.LabelSkillInfo;
import com.fatec.dto.LabelUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LabelRepository extends JpaRepository<LabelEntity, Long> {

}
