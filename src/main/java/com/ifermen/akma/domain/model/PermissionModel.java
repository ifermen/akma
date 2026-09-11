package com.ifermen.akma.domain.model;

import com.ifermen.akma.infraestructure.jpa.entity.KeyPermissionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
public class PermissionModel {

    private UUID id;

    private String url;

    private String method;

    private ServiceModel service;

    private String description;

    //private List<KeyPermissionEntity> keyPermissions;

    public void generateRandomId(){
        this.id = UUID.randomUUID();
    }
}
