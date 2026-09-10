package com.ifermen.akma.application.port.out.repository;

import com.ifermen.akma.domain.model.KeyModel;
import com.ifermen.akma.domain.model.PermissionModel;

import java.util.List;

public interface KeyRepository {
    KeyModel create(KeyModel keyModel);

    KeyModel addPermissions(KeyModel keyModel, List<PermissionModel> permissions);
}
