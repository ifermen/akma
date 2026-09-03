package com.ifermen.akma.infraestructure.adapter.in.web;

import com.ifermen.akma.application.command.permission.CreatePermissionCommand;
import com.ifermen.akma.application.port.in.permission.CreatePermissionUseCase;
import com.ifermen.akma.application.port.in.permission.GetPermissionUseCase;
import com.ifermen.akma.application.port.in.permission.ListPermissionUseCase;
import com.ifermen.akma.domain.model.PermissionModel;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.CreatePermissionRequest;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.PermissionResponse;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.PermissionWithServiceResponse;
import com.ifermen.akma.infraestructure.apidoc.PermissionControllerDoc;
import com.ifermen.akma.infraestructure.mapstruct.PermissionMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/permissions")
public class PermissionController implements PermissionControllerDoc {

    private PermissionMapper permissionMapper;
    private CreatePermissionUseCase createPermissionUseCase;
    private ListPermissionUseCase listPermissionUseCase;
    private GetPermissionUseCase getPermissionUseCase;

    @PostMapping("/{idService}")
    @Override
    public ResponseEntity<PermissionWithServiceResponse> createPermission(
            @PathVariable UUID idService,
            @Valid @RequestBody CreatePermissionRequest createPermissionRequest){
        CreatePermissionCommand command = this.permissionMapper.toCreatePermissionCommand(createPermissionRequest);
        command.setServiceId(idService);

        PermissionModel permissionModel = this.createPermissionUseCase.execute(command);
        PermissionWithServiceResponse permissionWithServiceResponse = this.permissionMapper.toPermissionWithServiceResponse(permissionModel);

        URI uri = URI.create("/permissions/" + permissionWithServiceResponse.getId());

        return ResponseEntity.created(uri).body(permissionWithServiceResponse);
    }

    @GetMapping("/{idService}")
    @Override
    public ResponseEntity<List<PermissionResponse>> listPermission(@PathVariable UUID idService){
        List<PermissionModel> permissionModelList = this.listPermissionUseCase.execute(idService);

        List<PermissionResponse> permissionResponseList =
                permissionModelList.stream().map(permissionMapper::toPermissionResponse).toList();

        return ResponseEntity.ok(permissionResponseList);
    }

    @GetMapping("/{idService}/{idPermission}")
    @Override
    public ResponseEntity<PermissionWithServiceResponse> getPermission(
            @PathVariable("idService") UUID idService,
            @PathVariable("idPermission") UUID idPermission){
        PermissionModel permissionModel = this.getPermissionUseCase.execute(idService,idPermission);
        PermissionWithServiceResponse permission = this.permissionMapper.toPermissionWithServiceResponse(permissionModel);

        return ResponseEntity.ok(permission);
    }
}
