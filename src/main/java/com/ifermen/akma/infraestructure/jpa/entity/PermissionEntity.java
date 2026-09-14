package com.ifermen.akma.infraestructure.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
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

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    //@OneToMany(mappedBy = "permission")
    //private List<KeyPermissionEntity> keyPermissions;
}
