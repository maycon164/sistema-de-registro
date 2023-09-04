package com.fatec.dataprovider.repository;

import com.fatec.dataprovider.entities.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long>, JpaSpecificationExecutor<SkillEntity> {

    @Query("""
            SELECT s FROM SkillEntity s
            WHERE s.label.id = :id
            """)
    List<SkillEntity> findAllByLabelId(Long id);
}
