package com.ifermen.akma.infraestructure.adapter.out.repository;

import com.ifermen.akma.application.exception.NotFoundException;
import com.ifermen.akma.application.port.out.repository.PermissionRepository;
import com.ifermen.akma.domain.model.PermissionModel;
import com.ifermen.akma.infraestructure.jpa.entity.PermissionEntity;
import com.ifermen.akma.infraestructure.jpa.repository.PermissionJpaRepository;
import com.ifermen.akma.infraestructure.mapstruct.PermissionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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
    public boolean existPermission(String url, String method){
        List<PermissionEntity> permissionEntities =
                this.permissionJpaRepository.searchByUrlAndMethod(url,method);

        return !permissionEntities.isEmpty();
    }

    @Override
    public List<PermissionModel> listByService(UUID serviceId){
        List<PermissionEntity> permissionEntities = this.permissionJpaRepository.findByServiceId(serviceId);

        return permissionEntities.stream().map(permissionMapper::toPermissionModel).toList();
    }

    @Override
    public PermissionModel findById(UUID serviceId){
        PermissionEntity permissionEntity =
                this.permissionJpaRepository.findById(serviceId).orElseThrow(
                        () -> new NotFoundException("Permission not found")
                );

        return this.permissionMapper.toPermissionModel(permissionEntity);
    }

    @Override
    public PermissionModel findPermissionFromServiceByUrlAndMethod(UUID serviceId, String url, String method){
        List<PermissionEntity> permissionEntities =
                this.permissionJpaRepository.searchFromServiceByUrlAndMethod(serviceId,url,method);

        PermissionEntity permissionEntity = permissionEntities
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Permission not found"));

        return this.permissionMapper.toPermissionModel(permissionEntity);
    }

    @Override
    public PermissionModel update(PermissionModel permissionModel){
        PermissionEntity permissionEntity = this.permissionMapper.toPermissionEntity(permissionModel);
        PermissionEntity savedPermissionEntity = this.permissionJpaRepository.save(permissionEntity);

        return this.permissionMapper.toPermissionModel(savedPermissionEntity);
    }
}
