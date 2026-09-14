package com.ifermen.akma.application.usecase.permission;

import com.ifermen.akma.application.dto.command.permission.DeletePermissionCommand;
import com.ifermen.akma.application.port.in.permission.DeletePermissionUseCase;
import com.ifermen.akma.application.port.out.repository.PermissionRepository;
import com.ifermen.akma.domain.model.PermissionModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class DeletePermissionUseCaseImpl implements DeletePermissionUseCase {

    private PermissionRepository permissionRepository;

    //TODO: Check if user has access to service
    @Override
    public PermissionModel execute(DeletePermissionCommand deletePermissionCommand){

        PermissionModel permissionModel =
                this.permissionRepository.findById(deletePermissionCommand.getIdPermission());

        permissionModel.setDeletedAt(LocalDateTime.now());

        return this.permissionRepository.update(permissionModel);
    }
}
