package com.fatec.dataprovider.repository;

import com.fatec.dataprovider.entities.SnapshotAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnapshotAnswerRepository extends JpaRepository<SnapshotAnswerEntity, Long> {
}
