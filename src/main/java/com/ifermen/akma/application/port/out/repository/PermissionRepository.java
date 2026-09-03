package com.ifermen.akma.application.port.out.repository;

import com.ifermen.akma.domain.model.PermissionModel;

public interface PermissionRepository {
    PermissionModel create(PermissionModel permission);

    boolean existPermission(String target, String privilege);
}
