package com.fatec.dataprovider.repository;

import com.fatec.dataprovider.entities.SnapshotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SnapshotRepository extends JpaRepository<SnapshotEntity, Long> {

    @Query("""
            SELECT s FROM SnapshotEntity s WHERE s.user.id = :userId ORDER BY s.createdAt DESC LIMIT 1
            """)
    SnapshotEntity findLastSnapshot(Long userId);
}
