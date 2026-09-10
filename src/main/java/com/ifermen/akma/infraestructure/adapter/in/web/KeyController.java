package com.ifermen.akma.infraestructure.adapter.in.web;

import com.ifermen.akma.application.command.key.CreateKeyCommand;
import com.ifermen.akma.application.port.in.key.CreateKeyUseCase;
import com.ifermen.akma.domain.model.KeyModel;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.key.CreateKeyRequest;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.key.KeyResponse;
import com.ifermen.akma.infraestructure.mapstruct.KeyMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/keys")
public class KeyController {

    private KeyMapper keyMapper;
    private CreateKeyUseCase createKeyUseCase;

    @PostMapping("/{serviceId}")
    public ResponseEntity<KeyResponse> createKey(@PathVariable UUID serviceId, @RequestBody CreateKeyRequest createKeyRequest){
        CreateKeyCommand command = this.keyMapper.toCreateKeyCommand(createKeyRequest);
        command.setServiceId(serviceId);

        KeyModel keyModel = this.createKeyUseCase.execute(command);

        KeyResponse keyResponse = this.keyMapper.toKeyResponse(keyModel);

        return ResponseEntity.status(201).body(keyResponse);
    }
}
