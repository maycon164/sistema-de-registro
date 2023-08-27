package com.fatec.dataprovider.repository;

import com.fatec.dataprovider.entities.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long>, JpaSpecificationExecutor<SkillEntity> {

}
