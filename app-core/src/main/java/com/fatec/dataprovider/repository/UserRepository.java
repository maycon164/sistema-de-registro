package com.fatec.dataprovider.repository;

import com.fatec.dataprovider.entities.UserEntity;
import com.fatec.model.User;
import com.fatec.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    UserEntity findByEmail(String email);

    @Query("""
            SELECT user FROM UserEntity user
            WHERE user.role = 'TEAM_LEADER'
            """)
    List<UserEntity> findAllTeamLeaders();
}
