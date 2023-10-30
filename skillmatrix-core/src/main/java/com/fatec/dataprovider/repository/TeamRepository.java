package com.fatec.dataprovider.repository;

import com.fatec.dataprovider.entities.TeamEntity;
import com.fatec.dataprovider.entities.UserEntity;
import com.fatec.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long>, JpaSpecificationExecutor<TeamEntity> {
}
