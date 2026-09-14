package com.ifermen.akma.application.port.in.permission;

import com.ifermen.akma.application.dto.command.permission.ListCreateBulkPermissionCommand;
import com.ifermen.akma.domain.model.PermissionModel;

import java.util.List;

public interface CreateBulkPermissionUseCase {
    List<PermissionModel> execute(ListCreateBulkPermissionCommand listCreateBulkPermissionCommand);
}
