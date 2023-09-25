package com.fatec.dataprovider.repository;

import com.fatec.dataprovider.entities.SnapshotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnapshotRepository extends JpaRepository<SnapshotEntity, Long> {
}
