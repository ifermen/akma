package com.ifermen.akma.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class KeyModel {

    private UUID id;

    private String name;

    private UUID userId;

    private ServiceModel service;

    private String key;

    private String keyHash;

    private String keyPrefix;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastUsedAt;

    private LocalDateTime expireAt;

    private LocalDateTime revokeAt;

    private List<KeyPermissionModel> keyPermissions;

    public void generateRandomId(){
        this.id = UUID.randomUUID();
    }
}
