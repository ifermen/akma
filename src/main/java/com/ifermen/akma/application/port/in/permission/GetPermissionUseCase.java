package com.ifermen.akma.application.port.in.permission;

import com.ifermen.akma.domain.model.PermissionModel;

import java.util.UUID;

public interface GetPermissionUseCase {
    PermissionModel execute(UUID serviceId, UUID permissionId);
}
