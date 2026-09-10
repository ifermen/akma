package com.ifermen.akma.infraestructure.mapstruct;

import com.ifermen.akma.application.command.key.CreateKeyCommand;
import com.ifermen.akma.domain.model.KeyModel;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.key.CreateKeyRequest;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.key.KeyResponse;
import com.ifermen.akma.infraestructure.jpa.entity.KeyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KeyMapper {

    CreateKeyCommand toCreateKeyCommand(CreateKeyRequest createKeyRequest);

    KeyResponse toKeyResponse(KeyModel keyModel);

    KeyEntity toKeyEntity(KeyModel keyModel);

    KeyModel toKeyModel(KeyEntity keyEntity);
}
