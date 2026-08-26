package com.ifermen.akma.infraestructure.jpa.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class KeyPermissionId implements Serializable {

    private UUID permission;
    private UUID key;
}
