package com.fatec.dataprovider.entities;

import com.fatec.model.LabelEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "label")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LabelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "label")
    @Enumerated(EnumType.STRING)
    private LabelEnum label;
}
