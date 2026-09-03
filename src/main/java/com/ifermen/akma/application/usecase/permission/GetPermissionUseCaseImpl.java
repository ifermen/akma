package com.ifermen.akma.application.usecase.permission;

import com.ifermen.akma.application.exception.NotFoundException;
import com.ifermen.akma.application.port.in.permission.GetPermissionUseCase;
import com.ifermen.akma.application.port.out.repository.PermissionRepository;
import com.ifermen.akma.application.port.out.repository.ServiceRepository;
import com.ifermen.akma.domain.model.PermissionModel;
import com.ifermen.akma.domain.model.ServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GetPermissionUseCaseImpl implements GetPermissionUseCase {

    private PermissionRepository permissionRepository;
    private ServiceRepository serviceRepository;

    @Override
    public PermissionModel execute(UUID serviceId, UUID permissionId){
        //TODO: Verify if the user has access to this service
        ServiceModel serviceModel = this.serviceRepository.findById(serviceId);
        PermissionModel permissionModel = this.permissionRepository.findById(permissionId);

        if(!permissionModel.getService().getId().equals(serviceModel.getId())){
            throw new NotFoundException("Permission not found");
        }

        return permissionModel;
    }
}
