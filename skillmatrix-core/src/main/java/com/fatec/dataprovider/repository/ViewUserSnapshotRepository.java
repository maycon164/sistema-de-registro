package com.fatec.dataprovider.repository;

import com.fatec.dataprovider.view.ViewUserAndSnapshot;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ViewUserSnapshotRepository extends PagingAndSortingRepository<ViewUserAndSnapshot, Long>, JpaSpecificationExecutor<ViewUserAndSnapshot> {
    List<ViewUserAndSnapshot> findAll();
}
