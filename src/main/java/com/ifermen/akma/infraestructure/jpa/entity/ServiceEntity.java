package com.ifermen.akma.infraestructure.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "SERVICE")
@Data
public class ServiceEntity {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "service")
    private List<KeyEntity> keys;

    @OneToMany(mappedBy = "service")
    private List<PermissionEntity> permissions;
}
