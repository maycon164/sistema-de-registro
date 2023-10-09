package com.fatec.dataprovider.view;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "view_user_last_snapshot")
@Subselect("select v.* from view_user_last_snapshot v")
@Data

public class ViewUserAndSnapshot implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "skill_id")
    private Long skillId;

    @Column(name = "skill_name")
    private String skillName;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "level")
    private String level;

    @Column(name = "snapshot_id")
    private Long snapshotId;

    @Column(name = "last_snapshot")
    private LocalDate lastSnapshot;
}
