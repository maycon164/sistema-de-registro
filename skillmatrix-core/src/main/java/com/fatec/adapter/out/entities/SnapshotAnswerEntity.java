package com.fatec.adapter.out.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "snapshot_answer")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SnapshotAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "snapshot_id")
    private Long snapshotId;

    @ManyToOne
    private SkillEntity skill;

    @Column(name = "rating")
    private Integer rating;
}
