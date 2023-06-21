package com.fatec.adapter.out.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "snapshots")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SnapshotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @Column(name = "created_at")
    private Date createdAt;
}
