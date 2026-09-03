package com.ifermen.akma.infraestructure.adapter.out.repository;

import com.ifermen.akma.application.port.out.repository.PermissionRepository;
import com.ifermen.akma.domain.model.PermissionModel;
import com.ifermen.akma.infraestructure.jpa.entity.PermissionEntity;
import com.ifermen.akma.infraestructure.jpa.repository.PermissionJpaRepository;
import com.ifermen.akma.infraestructure.mapstruct.PermissionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PermissionRepositoryImpl implements PermissionRepository {

    private PermissionJpaRepository permissionJpaRepository;
    private PermissionMapper permissionMapper;

    @Override
    public PermissionModel create(PermissionModel permission){
        PermissionEntity permissionEntity = this.permissionMapper.toPermissionEntity(permission);

        PermissionEntity savedPermissionEntity = this.permissionJpaRepository.save(permissionEntity);

        return this.permissionMapper.toPermissionModel(savedPermissionEntity);
    }

    @Override
    public boolean existPermission(String target, String privilege){
        List<PermissionEntity> permissionEntities =
                this.permissionJpaRepository.searchByTargetAndPrivilege(target,privilege);

        return !permissionEntities.isEmpty();
    }
}
