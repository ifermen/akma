package com.ifermen.akma.domain.model;

import com.ifermen.akma.infraestructure.jpa.entity.KeyEntity;
import com.ifermen.akma.infraestructure.jpa.entity.PermissionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

public class ServiceModel {

    private UUID id;

    private String name;

    private String description;

    private List<KeyModel> keys;

    private List<PermissionModel> permissions;
}
