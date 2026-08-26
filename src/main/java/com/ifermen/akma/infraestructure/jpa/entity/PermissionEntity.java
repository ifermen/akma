package com.ifermen.akma.infraestructure.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PERMISSION")
@Data
public class PermissionEntity {

    @Id
    private UUID id;

    @Column(name = "target")
    private String target;

    @Column(name = "privilege")
    private String privilege;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "permission")
    private List<KeyPermissionEntity> keyPermissions;
}
