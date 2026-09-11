package com.ifermen.akma.infraestructure.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "API_KEY")
@Data
public class KeyEntity {
    @Id
    private UUID id;

    @Column(name = "env")
    private String env;

    @Column(name = "user_id")
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @Column(name = "key_hash")
    private String keyHash;

    @Column(name = "key_prefix")
    private String keyPrefix;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "last_used_at")
    private LocalDateTime lastUsedAt;

    @Column(name = "expires_at")
    private LocalDateTime expireAt;

    @Column(name = "revoked_at")
    private LocalDateTime revokeAt;

    @OneToMany(mappedBy = "key")
    private List<KeyPermissionEntity> keyPermissions;
}
