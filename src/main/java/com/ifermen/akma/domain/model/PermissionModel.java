package com.ifermen.akma.domain.model;

import com.ifermen.akma.infraestructure.jpa.entity.KeyPermissionEntity;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PermissionModel {

    private UUID id;

    private String target;

    private String privilege;

    private ServiceModel service;

    private String description;

    private List<KeyPermissionEntity> keyPermissions;

    public void generateRandomId(){
        this.id = UUID.randomUUID();
    }
}
