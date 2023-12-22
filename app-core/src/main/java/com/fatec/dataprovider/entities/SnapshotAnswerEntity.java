package com.fatec.dataprovider.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "snapshot_answer")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SnapshotAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Long id;

    @JoinColumn(name = "snapshot_id")
    @ManyToOne
    private SnapshotEntity snapshotId;

    @ManyToOne
    private SkillEntity skill;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "willing_to_answer_questions")
    private Boolean willingToAnswerQuestions;

    @Column(name = "willing_to_present")
    private Boolean willingToPresent;

    @Column(name = "worked_with_tech")
    private Boolean workedWithTech;
}
