package com.ifermen.akma.application.usecase.permission;

import com.ifermen.akma.application.dto.command.permission.ListCreateBulkPermissionCommand;
import com.ifermen.akma.application.dto.command.permission.CreateBulkPermission;
import com.ifermen.akma.application.mapper.PermissionMapperApplication;
import com.ifermen.akma.application.port.in.permission.CreateBulkPermissionUseCase;
import com.ifermen.akma.application.port.out.repository.PermissionRepository;
import com.ifermen.akma.application.port.out.repository.ServiceRepository;
import com.ifermen.akma.domain.model.PermissionModel;
import com.ifermen.akma.domain.model.ServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CreateBulkPermissionUseCaseImpl implements CreateBulkPermissionUseCase {

    private PermissionRepository permissionRepository;
    private ServiceRepository serviceRepository;
    private PermissionMapperApplication permissionMapperApplication;

    @Override
    public List<PermissionModel> execute(ListCreateBulkPermissionCommand listCreateBulkPermissionCommand){

        ServiceModel serviceModel = this.serviceRepository.findById(listCreateBulkPermissionCommand.getServiceId());

        List<PermissionModel> createdPermissions = new ArrayList<>();
        for (CreateBulkPermission createBulkPermission : listCreateBulkPermissionCommand.getPermissions()) {
            PermissionModel permissionModel = this.permissionMapperApplication.toPermissionModel(createBulkPermission);
            permissionModel.generateRandomId();
            permissionModel.setService(serviceModel);

            PermissionModel created = this.permissionRepository.create(permissionModel);
            createdPermissions.add(created);
        }

        return createdPermissions;
    }
}
