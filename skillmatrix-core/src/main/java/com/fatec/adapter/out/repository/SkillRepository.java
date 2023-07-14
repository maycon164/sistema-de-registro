package com.fatec.adapter.out.repository;

import com.fatec.adapter.out.entities.SkillEntity;
import com.fatec.model.LabelsEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

    @Query("""
            SELECT skl FROM SkillEntity skl
            JOIN skl.label lbl
            WHERE lbl.label = :label
            """)
    List<SkillEntity> findByLabel(String label);
}
