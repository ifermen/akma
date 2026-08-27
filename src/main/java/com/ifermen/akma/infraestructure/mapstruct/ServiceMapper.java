package com.ifermen.akma.infraestructure.mapstruct;

import com.ifermen.akma.application.command.CreateServiceCommand;
import com.ifermen.akma.domain.model.ServiceModel;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.CreateServiceRequest;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.CreateServiceResponse;
import com.ifermen.akma.infraestructure.jpa.entity.ServiceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    CreateServiceCommand toCreateServiceRequest(CreateServiceRequest createServiceRequest);

    CreateServiceResponse toCreateServiceResponse(ServiceModel serviceModel);

    ServiceEntity toServiceEntity(CreateServiceCommand createServiceCommand);

    ServiceModel toServiceModel(ServiceEntity serviceEntity);
}
