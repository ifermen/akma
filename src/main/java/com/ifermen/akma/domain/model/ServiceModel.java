package com.ifermen.akma.domain.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ServiceModel {

    private UUID id;

    private String name;

    private String description;

    private List<KeyModel> keys;

    private List<PermissionModel> permissions;
}
