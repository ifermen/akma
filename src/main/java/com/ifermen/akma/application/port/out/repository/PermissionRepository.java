package com.ifermen.akma.application.port.out.repository;

import com.ifermen.akma.domain.model.PermissionModel;

import java.util.List;
import java.util.UUID;

public interface PermissionRepository {
    PermissionModel create(PermissionModel permission);

    boolean existPermission(String target, String privilege);

    List<PermissionModel> listByService(UUID serviceId);

    PermissionModel findById(UUID serviceId);
}
