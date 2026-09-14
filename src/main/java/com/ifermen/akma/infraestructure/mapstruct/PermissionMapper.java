package com.ifermen.akma.infraestructure.mapstruct;


import com.ifermen.akma.application.dto.command.permission.CreatePermissionCommand;
import com.ifermen.akma.application.dto.command.permission.CreateBulkPermission;
import com.ifermen.akma.application.dto.command.permission.UpdatePermissionCommand;
import com.ifermen.akma.domain.model.PermissionModel;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.CreatePermissionRequest;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.PermissionResponse;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.PermissionWithServiceResponse;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.UpdatePermissionRequest;
import com.ifermen.akma.infraestructure.jpa.entity.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    @Mapping(target = "method", expression = "java(createPermissionRequest.getMethod().toUpperCase())")
    CreatePermissionCommand toCreatePermissionCommand(CreatePermissionRequest createPermissionRequest);

    PermissionEntity toPermissionEntity(PermissionModel permissionModel);

    PermissionWithServiceResponse toPermissionWithServiceResponse(PermissionModel permissionModel);

    PermissionModel toPermissionModel(PermissionEntity permissionEntity);

    PermissionResponse toPermissionResponse(PermissionModel permissionModel);

    UpdatePermissionCommand toUpdatePermissionCommand(UpdatePermissionRequest updatePermissionRequest);

    CreateBulkPermission toCreatePermissionForBulk(CreatePermissionRequest createPermissionRequest);
}
