package com.ifermen.akma.application.port.out.repository;

import com.ifermen.akma.domain.model.KeyModel;
import com.ifermen.akma.domain.model.PermissionModel;

import java.util.List;
import java.util.UUID;

public interface KeyRepository {
    KeyModel create(KeyModel keyModel);

    KeyModel addPermissions(KeyModel keyModel, List<PermissionModel> permissions);

    List<KeyModel> findByPrefix(String prefix);

    KeyModel update(KeyModel keyModel);

    List<KeyModel> searchByServiceIdEnvUserIdAndRevokeAtNull(UUID serviceId, String env, UUID userId);
}
