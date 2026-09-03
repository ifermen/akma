package com.ifermen.akma.application.usecase.permission;

import com.ifermen.akma.application.port.in.permission.ListPermissionUseCase;
import com.ifermen.akma.application.port.out.repository.PermissionRepository;
import com.ifermen.akma.domain.model.PermissionModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ListPermissionUseCaseImpl implements ListPermissionUseCase {

    private PermissionRepository permissionRepository;

    @Override
    public List<PermissionModel> execute(UUID serviceId){
        return this.permissionRepository.listByService(serviceId);
    }
}
