package com.fatec.dataprovider.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Table(name = "teams")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    private UserEntity leader;

    @ManyToOne
    private ClientEntity client;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDate updatedAt;

    @OneToMany
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private List<UserEntity> members;

    @Override
    public String toString() {
        return "OVERRIDE METHOD TO STRING";
    }
}
