package com.ifermen.akma.application.mapper;

import com.ifermen.akma.application.dto.command.permission.CreateBulkPermission;
import com.ifermen.akma.application.dto.command.permission.CreatePermissionCommand;
import com.ifermen.akma.domain.model.PermissionModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapperApplication {

    PermissionModel toPermissionModel(CreatePermissionCommand createPermissionCommand);

    PermissionModel toPermissionModel(CreateBulkPermission createBulkPermission);
}
