package com.ifermen.akma.application.usecase.permission;

import com.ifermen.akma.application.dto.command.permission.UpdatePermissionCommand;
import com.ifermen.akma.application.port.in.permission.UpdatePermissionUseCase;
import com.ifermen.akma.application.port.out.repository.PermissionRepository;
import com.ifermen.akma.domain.model.PermissionModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdatePermissionUseCaseImpl implements UpdatePermissionUseCase {

    private PermissionRepository permissionRepository;

    //TODO: Check if user has access to the service. And update service
    @Override
    public PermissionModel execute(UpdatePermissionCommand updatePermissionCommand){
        PermissionModel permissionModel = this.permissionRepository.findById(updatePermissionCommand.getPermissionId());

        updateUrl(permissionModel,updatePermissionCommand.getUrl());
        updateMethod(permissionModel, updatePermissionCommand.getMethod());
        updateDescription(permissionModel, updatePermissionCommand.getDescription());

        return this.permissionRepository.update(permissionModel);
    }

    private void updateUrl(PermissionModel permissionModel, String subject){
        if(subject != null && !subject.isBlank()){

            String url = subject.trim();

            if(!permissionModel.getUrl().equals(url)){
                permissionModel.setUrl(url);
            }
        }
    }

    private void updateMethod(PermissionModel permissionModel, String subject){
        if(subject != null && !subject.isBlank()){

            String method = subject.toUpperCase().trim();

            if(!permissionModel.getMethod().equals(method)){
                permissionModel.setMethod(method);
            }
        }
    }

    private void updateDescription(PermissionModel permissionModel, String subject){
        if(subject != null){
            permissionModel.setDescription(subject.trim());
        }else{
            permissionModel.setDescription(null);
        }
    }
}
