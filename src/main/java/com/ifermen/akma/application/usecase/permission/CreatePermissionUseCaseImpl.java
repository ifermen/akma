package com.ifermen.akma.application.usecase.permission;

import com.ifermen.akma.application.command.permission.CreatePermissionCommand;
import com.ifermen.akma.application.exception.ConfilctException;
import com.ifermen.akma.application.mapper.PermissionMapperApplication;
import com.ifermen.akma.application.port.in.permission.CreatePermissionUseCase;
import com.ifermen.akma.application.port.out.repository.PermissionRepository;
import com.ifermen.akma.application.port.out.repository.ServiceRepository;
import com.ifermen.akma.domain.model.PermissionModel;
import com.ifermen.akma.domain.model.ServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatePermissionUseCaseImpl implements CreatePermissionUseCase {

    private ServiceRepository serviceRepository;
    private PermissionRepository permissionRepository;
    private PermissionMapperApplication permissionMapperApplication;

    @Override
    public PermissionModel execute(CreatePermissionCommand createPermissionCommand){

        if(this.permissionRepository.existPermission(
                createPermissionCommand.getTarget(),
                createPermissionCommand.getPrivilege())){
            throw new ConfilctException("This permission already exist");
        }

        ServiceModel serviceModel = this.serviceRepository.findById(createPermissionCommand.getServiceId());
        PermissionModel permissionModel = this.permissionMapperApplication.toPermissionModel(createPermissionCommand);

        permissionModel.generateRandomId();
        permissionModel.setService(serviceModel);

        return this.permissionRepository.create(permissionModel);
    }
}
