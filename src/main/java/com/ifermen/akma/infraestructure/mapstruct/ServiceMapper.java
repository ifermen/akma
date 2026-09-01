package com.ifermen.akma.infraestructure.mapstruct;

import com.ifermen.akma.application.command.CreateServiceCommand;
import com.ifermen.akma.application.command.UpdateServiceCommand;
import com.ifermen.akma.domain.model.ServiceModel;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.CreateServiceRequest;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.ServiceResponse;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.UpdateServiceRequest;
import com.ifermen.akma.infraestructure.jpa.entity.ServiceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper{

    CreateServiceCommand toCreateServiceCommand(CreateServiceRequest createServiceRequest);

    ServiceResponse toServiceResponse(ServiceModel serviceModel);

    ServiceEntity toServiceEntity(ServiceModel serviceModel);

    ServiceModel toServiceModel(ServiceEntity serviceEntity);

    UpdateServiceCommand toUpdateServiceCommand(UpdateServiceRequest updateServiceRequest);
}
