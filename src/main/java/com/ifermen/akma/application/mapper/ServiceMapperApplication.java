package com.ifermen.akma.application.mapper;

import com.ifermen.akma.application.command.service.CreateServiceCommand;
import com.ifermen.akma.application.command.service.UpdateServiceCommand;
import com.ifermen.akma.domain.model.ServiceModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapperApplication {

    ServiceModel toServiceModel(CreateServiceCommand createServiceCommand);

    ServiceModel toServiceModel(UpdateServiceCommand updateServiceCommand);
}
