package com.ifermen.akma.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class KeyModel {

    private UUID id;

    private String name;

    private UUID user;

    private ServiceModel service;

    private String key_hash;

    private String keyPrefix;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastUsedAt;

    private LocalDateTime expireAt;

    private LocalDateTime revokeAt;

    private List<KeyPermissionModel> keyPermissions;
}
