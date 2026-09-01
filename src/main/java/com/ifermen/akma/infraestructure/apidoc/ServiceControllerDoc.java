package com.ifermen.akma.infraestructure.apidoc;

import com.ifermen.akma.infraestructure.adapter.in.web.dto.error.ApiError;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.error.ApiErrorMessageList;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.CreateServiceRequest;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.ServiceResponse;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.UpdateServiceRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Tag(name = "Servicios", description = "Enpoints para gestionar los servicios registrados en AKMA")
public interface ServiceControllerDoc {


    @Operation(summary = "Crear servicio", description = "Crea un servicio")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Creado",
                    content = @Content(schema = @Schema(implementation = ServiceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error de validación",
                    content = @Content(schema = @Schema(implementation = ApiErrorMessageList.class))),
            @ApiResponse(responseCode = "409", description = "Ya existe un servicio con el mismo nombre",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    ResponseEntity<ServiceResponse> createService(CreateServiceRequest request);

    // Clase auxiliar para Swagger
    class ListServiceResponse extends ArrayList<ServiceResponse> {}
    @Operation(summary = "Listar Servicios", description = "Lista todos los servicios de la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado",
            content = @Content(schema = @Schema(implementation = ListServiceResponse.class)))
    })
    ResponseEntity<List<ServiceResponse>> listServices();

    @Operation(summary = "Obtener un servicio", description = "Obtiene un servicio por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Obtenido",
                    content = @Content(schema = @Schema(implementation = ServiceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error de formato de id",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Servicio no encontrado",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    ResponseEntity<ServiceResponse> getService(UUID id);

    @Operation(summary = "Actualizar servicio", description = "Actualiza el servicio por id")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Actualizado",
                    content = @Content(schema = @Schema(implementation = ServiceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error de validación",
                    content = @Content(schema = @Schema(implementation = ApiErrorMessageList.class))),
            @ApiResponse(responseCode = "404", description = "Servicio no encontrado",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "Ya exite un servicio con el mismo nombre",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    ResponseEntity<ServiceResponse> updateService(UUID id, UpdateServiceRequest updateServiceRequest);

    @Operation(summary = "Eliminar un servicio", description = "Elimina un servicio por id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Borrado",
                    content = @Content(schema = @Schema(implementation = ServiceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error de formato de id",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Servicio no encontrado",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    ResponseEntity<?> deleteService(UUID id);
}
