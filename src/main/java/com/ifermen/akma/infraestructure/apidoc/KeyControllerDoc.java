package com.ifermen.akma.infraestructure.apidoc;

import com.ifermen.akma.infraestructure.adapter.in.web.dto.error.ApiError;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.error.ApiErrorMessageList;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.key.*;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.ServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Tag(name = "Claves", description = "Enpoints para para la creación y uso de claves")
public interface KeyControllerDoc {

    @Operation(summary = "Crear clave", description = "Crea una clave")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Creado",
                    content = @Content(schema = @Schema(implementation = ServiceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error en la petición",
                    content = @Content(schema = @Schema(implementation = ApiErrorMessageList.class))),
            @ApiResponse(responseCode = "404", description = "Servicio o permiso no encontrado",
                    content = @Content(schema = @Schema(implementation = ApiErrorMessageList.class)))
    })
    ResponseEntity<KeyResponse> createKey(UUID serviceId, CreateKeyRequest createKeyRequest);

    @Operation(summary = "Validar petición", description = "Valida una clave para un petición concreta")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Creado",
                    content = @Content(schema = @Schema(implementation = ServiceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error en la petición",
                    content = @Content(schema = @Schema(implementation = ApiErrorMessageList.class))),
            @ApiResponse(responseCode = "404", description = "Servicio, permiso o clave no encontrado",
                    content = @Content(schema = @Schema(implementation = ApiErrorMessageList.class)))
    })
    ResponseEntity<ValidateKeyResponse> validateRequest(UUID serviceId, ValidateKeyRequest validateKeyRequest);

    @Operation(summary = "Revocar clave", description = "Revocar una clave")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Revocada",
                    content = @Content(schema = @Schema(implementation = ServiceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error en la petición",
                    content = @Content(schema = @Schema(implementation = ApiErrorMessageList.class))),
            @ApiResponse(responseCode = "404", description = "Servicio, o clave no encontrado",
                    content = @Content(schema = @Schema(implementation = ApiErrorMessageList.class)))
    })
    ResponseEntity<?> revokeKey(UUID serviceId, RevokeKeyRequest revokeKeyRequest);
}
