package com.ifermen.akma.infraestructure.apidoc;

import com.ifermen.akma.infraestructure.adapter.in.web.dto.error.ApiError;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.error.ApiErrorMessageList;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.CreatePermissionRequest;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.PermissionResponse;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.PermissionWithServiceResponse;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.permission.UpdatePermissionRequest;
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
            @ApiResponse(responseCode = "400", description = "Error en la petición",
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

    @Operation(summary = "Obtener un permiso", description = "Obtiene un permiso por su id y el id de su servicio")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Obtenido",
                    content = @Content(schema = @Schema(implementation = PermissionWithServiceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error de formato de id",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Permiso no encontrado o no perteneciente a ese servicio",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    ResponseEntity<PermissionWithServiceResponse> getPermission(UUID idService, UUID idPermission);

    @Operation(summary = "Actualizar un permiso", description = "Actualiza un permiso")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Actualizado",
                    content = @Content(schema = @Schema(implementation = PermissionWithServiceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error en la petición",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Permiso no encontrado",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    ResponseEntity<PermissionWithServiceResponse> updatePermission(
            UUID idService,
            UUID idPermission,
            UpdatePermissionRequest updatePermissionRequest);

    @Operation(summary = "Borrar un permiso", description = "Borra de manera lógica un permiso")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Borrado",
                    content = @Content(schema = @Schema(implementation = PermissionWithServiceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error de formato de id",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Permiso no encontrado",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    ResponseEntity<PermissionWithServiceResponse> deletePermission(UUID idService, UUID idPermission);

    @Operation(summary = "Crear permisos bulk", description = "Crea varios permisos vinculado a un servicio")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Creado",
                    content = @Content(schema = @Schema(implementation = PermissionResponse[].class))),
            @ApiResponse(responseCode = "400", description = "Error en la petición",
                    content = @Content(schema = @Schema(implementation = ApiErrorMessageList.class))),
            @ApiResponse(responseCode = "409", description = "Ya existe un permiso que coincide tanto en 'target' como en 'privilege'",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    ResponseEntity<List<PermissionResponse>> createPermissionBulk(UUID idService, List<CreatePermissionRequest> createPermissionRequests);
}
