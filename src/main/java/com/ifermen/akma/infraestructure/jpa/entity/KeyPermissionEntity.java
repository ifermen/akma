package com.ifermen.akma.infraestructure.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "KEY_PERMISSION")
@IdClass(KeyPermissionId.class)
@Data
public class KeyPermissionEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id")
    private PermissionEntity permission;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "key_id")
    private KeyEntity key;
}
