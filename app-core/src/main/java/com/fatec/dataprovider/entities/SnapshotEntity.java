package com.fatec.dataprovider.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Table(name = "snapshots")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SnapshotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Long id;

    @ManyToOne
    private UserEntity user;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @OneToMany
    @JoinColumn(name = "snapshot_id", referencedColumnName = "id")
    private List<SnapshotAnswerEntity> answers;
}
