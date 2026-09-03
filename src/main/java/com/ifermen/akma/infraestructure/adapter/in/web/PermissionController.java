package com.ifermen.akma.infraestructure.adapter.in.web;

import com.ifermen.akma.application.command.permission.CreatePermissionCommand;
import com.ifermen.akma.application.port.in.permission.CreatePermissionUseCase;
import com.ifermen.akma.domain.model.PermissionModel;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.CreatePermissionRequest;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.PermissionResponse;
import com.ifermen.akma.infraestructure.apidoc.PermissionControllerDoc;
import com.ifermen.akma.infraestructure.mapstruct.PermissionMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/permissions")
public class PermissionController implements PermissionControllerDoc {

    private PermissionMapper permissionMapper;
    private CreatePermissionUseCase createPermissionUseCase;

    @PostMapping("/{idService}")
    @Override
    public ResponseEntity<PermissionResponse> createPermission(
            @PathVariable UUID idService,
            @Valid @RequestBody CreatePermissionRequest createPermissionRequest){
        CreatePermissionCommand command = this.permissionMapper.toCreatePermissionCommand(createPermissionRequest);
        command.setServiceId(idService);

        PermissionModel permissionModel = this.createPermissionUseCase.execute(command);
        PermissionResponse permissionResponse = this.permissionMapper.toPermissionResponse(permissionModel);

        URI uri = URI.create("/permissions/" + permissionResponse.getId());

        return ResponseEntity.created(uri).body(permissionResponse);
    }
}
