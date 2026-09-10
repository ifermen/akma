package com.ifermen.akma.infraestructure.mapstruct;

import com.ifermen.akma.domain.model.KeyPermissionModel;
import com.ifermen.akma.infraestructure.jpa.entity.KeyPermissionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KeyPermissionMapper {

    KeyPermissionModel toKeyPermissionModel(KeyPermissionEntity keyPermissionEntity);
}
