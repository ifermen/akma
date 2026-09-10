package com.ifermen.akma.infraestructure.adapter.out.repository;

import com.ifermen.akma.application.port.out.repository.KeyRepository;
import com.ifermen.akma.domain.model.KeyModel;
import com.ifermen.akma.domain.model.KeyPermissionModel;
import com.ifermen.akma.domain.model.PermissionModel;
import com.ifermen.akma.infraestructure.jpa.entity.KeyEntity;
import com.ifermen.akma.infraestructure.jpa.entity.KeyPermissionEntity;
import com.ifermen.akma.infraestructure.jpa.entity.PermissionEntity;
import com.ifermen.akma.infraestructure.jpa.repository.KeyJpaRepository;
import com.ifermen.akma.infraestructure.jpa.repository.KeyPermissionJpaRepository;
import com.ifermen.akma.infraestructure.mapstruct.KeyMapper;
import com.ifermen.akma.infraestructure.mapstruct.KeyPermissionMapper;
import com.ifermen.akma.infraestructure.mapstruct.PermissionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class KeyRepositoryImpl implements KeyRepository {

    private KeyJpaRepository keyJpaRepository;
    private KeyPermissionJpaRepository keyPermissionJpaRepository;
    private KeyMapper keyMapper;
    private PermissionMapper permissionMapper;
    private KeyPermissionMapper keyPermissionMapper;

    @Override
    public KeyModel create(KeyModel keyModel){
        KeyEntity keyEntity = this.keyMapper.toKeyEntity(keyModel);
        KeyEntity created = this.keyJpaRepository.save(keyEntity);

        return this.keyMapper.toKeyModel(created);
    }

    @Override
    public KeyModel addPermissions(KeyModel keyModel, List<PermissionModel> permissions){
        KeyEntity keyEntity = this.keyMapper.toKeyEntity(keyModel);
        List<KeyPermissionEntity> keyPermissionEntities = new ArrayList<>();
        List<KeyPermissionModel> createds = new ArrayList<>();

        permissions.forEach(p -> {
            PermissionEntity permissionEntity = this.permissionMapper.toPermissionEntity(p);

            KeyPermissionEntity keyPermissionEntity  = new KeyPermissionEntity();
            keyPermissionEntity.setPermission(permissionEntity);
            keyPermissionEntity.setKey(keyEntity);

            KeyPermissionEntity created = this.keyPermissionJpaRepository.save(keyPermissionEntity);

            KeyPermissionModel keyPermissionModel = this.keyPermissionMapper.toKeyPermissionModel(created);
            createds.add(keyPermissionModel);
        });

        keyModel.setKeyPermissions(createds);
        return keyModel;
    }
}
