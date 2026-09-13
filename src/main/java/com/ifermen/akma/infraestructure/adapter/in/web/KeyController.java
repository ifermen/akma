package com.ifermen.akma.infraestructure.adapter.in.web;

import com.ifermen.akma.application.dto.command.key.CreateKeyCommand;
import com.ifermen.akma.application.dto.query.ValidateRequestQuery;
import com.ifermen.akma.application.dto.result.ValidateRequestResult;
import com.ifermen.akma.application.port.in.key.CreateKeyUseCase;
import com.ifermen.akma.application.port.in.key.ValidateRequestUseCase;
import com.ifermen.akma.domain.model.KeyModel;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.key.CreateKeyRequest;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.key.KeyResponse;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.key.ValidateKeyRequest;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.key.ValidateKeyResponse;
import com.ifermen.akma.infraestructure.apidoc.KeyControllerDoc;
import com.ifermen.akma.infraestructure.mapstruct.KeyMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/keys")
public class KeyController implements KeyControllerDoc {

    private KeyMapper keyMapper;
    private CreateKeyUseCase createKeyUseCase;
    private ValidateRequestUseCase validateRequestUseCase;

    @PostMapping("/{serviceId}")
    @Override
    public ResponseEntity<KeyResponse> createKey(
            @PathVariable UUID serviceId,
            @Valid @RequestBody CreateKeyRequest createKeyRequest){

        CreateKeyCommand command = this.keyMapper.toCreateKeyCommand(createKeyRequest);
        command.setServiceId(serviceId);

        KeyModel keyModel = this.createKeyUseCase.execute(command);

        KeyResponse keyResponse = this.keyMapper.toKeyResponse(keyModel);

        return ResponseEntity.status(201).body(keyResponse);
    }

    @PostMapping("/{serviceId}/validate")
    @Override
    public ResponseEntity<ValidateKeyResponse> validateRequest(
            @PathVariable UUID serviceId,
            @Valid @RequestBody ValidateKeyRequest validateKeyRequest){

        ValidateRequestQuery validateRequestQuery = this.keyMapper.toValidateRequestQuery(validateKeyRequest);
        validateRequestQuery.setServiceId(serviceId);

        ValidateRequestResult validateRequestResult = this.validateRequestUseCase.execute(validateRequestQuery);
        ValidateKeyResponse validateKeyResponse = this.keyMapper.toValidateKeyResponse(validateRequestResult);

        return ResponseEntity.ok(validateKeyResponse);
    }
}
