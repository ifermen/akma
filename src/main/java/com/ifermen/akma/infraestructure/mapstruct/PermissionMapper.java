package com.ifermen.akma.infraestructure.mapstruct;


import com.ifermen.akma.application.command.permission.CreatePermissionCommand;
import com.ifermen.akma.domain.model.PermissionModel;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.CreatePermissionRequest;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.PermissionResponse;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.PermissionWithServiceResponse;
import com.ifermen.akma.infraestructure.jpa.entity.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    @Mapping(target = "target", expression = "java(createPermissionRequest.getTarget().toUpperCase())")
    @Mapping(target = "privilege", expression = "java(createPermissionRequest.getPrivilege().toUpperCase())")
    CreatePermissionCommand toCreatePermissionCommand(CreatePermissionRequest createPermissionRequest);

    PermissionEntity toPermissionEntity(PermissionModel permissionModel);

    PermissionWithServiceResponse toPermissionWithServiceResponse(PermissionModel permissionModel);

    PermissionModel toPermissionModel(PermissionEntity permissionEntity);

    PermissionResponse toPermissionResponse(PermissionModel permissionModel);
}
