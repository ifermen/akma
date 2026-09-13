package com.ifermen.akma.application.port.in.permission;

import com.ifermen.akma.application.dto.command.permission.UpdatePermissionCommand;
import com.ifermen.akma.domain.model.PermissionModel;

public interface UpdatePermissionUseCase {
    PermissionModel execute(UpdatePermissionCommand updatePermissionCommand);
}
