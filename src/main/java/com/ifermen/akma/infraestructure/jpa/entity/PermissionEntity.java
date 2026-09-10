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

    @Column(name = "url")
    private String url;

    @Column(name = "method")
    private String method;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "permission")
    private List<KeyPermissionEntity> keyPermissions;
}
