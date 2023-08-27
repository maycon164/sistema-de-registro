package com.fatec.dataprovider.repository;

import com.fatec.dataprovider.entities.LabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<LabelEntity, Long> {
}
