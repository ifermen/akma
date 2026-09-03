package com.ifermen.akma.infraestructure.apidoc;

import com.ifermen.akma.infraestructure.adapter.in.web.dto.error.ApiError;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.error.ApiErrorMessageList;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.CreatePermissionRequest;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.PermissionResponse;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.PermissionWithServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Tag(name = "Permisos", description = "Enpoints para gestionar los permisos disponibles de los servicios registrados en AKMA")
public interface PermissionControllerDoc {

    @Operation(summary = "Crear permiso", description = "Crea un permiso vinculado a un servicio")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Creado",
                    content = @Content(schema = @Schema(implementation = PermissionWithServiceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error de validación",
                    content = @Content(schema = @Schema(implementation = ApiErrorMessageList.class))),
            @ApiResponse(responseCode = "409", description = "Ya existe un permiso que coincide tanto en 'target' como en 'privilege'",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    ResponseEntity<PermissionWithServiceResponse> createPermission(UUID idService, CreatePermissionRequest createPermissionRequest);

    @Operation(summary = "Listar permisos", description = "Lista todos los permisos de un servicio")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado",
                    content = @Content(schema = @Schema(implementation = PermissionResponse[].class)))
    })
    ResponseEntity<List<PermissionResponse>> listPermission(UUID idService);
}
