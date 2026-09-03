package com.ifermen.akma.application.port.in.permission;

import com.ifermen.akma.application.command.permission.CreatePermissionCommand;
import com.ifermen.akma.domain.model.PermissionModel;

public interface CreatePermissionUseCase {
    PermissionModel execute(CreatePermissionCommand createPermissionCommand);
}
