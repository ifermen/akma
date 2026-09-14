package com.ifermen.akma.application.port.in.permission;

import com.ifermen.akma.application.dto.command.permission.DeletePermissionCommand;
import com.ifermen.akma.domain.model.PermissionModel;

public interface DeletePermissionUseCase {
    //TODO: Check if user has access to service
    PermissionModel execute(DeletePermissionCommand deletePermissionCommand);
}
