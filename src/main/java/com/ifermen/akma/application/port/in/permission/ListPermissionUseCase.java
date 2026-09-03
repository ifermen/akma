package com.ifermen.akma.application.port.in.permission;

import com.ifermen.akma.domain.model.PermissionModel;

import java.util.List;
import java.util.UUID;

public interface ListPermissionUseCase {
    List<PermissionModel> execute(UUID serviceId);
}
