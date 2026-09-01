package com.ifermen.akma.application.usecase.service;

import com.ifermen.akma.application.command.CreateServiceCommand;
import com.ifermen.akma.application.exception.ConfilctException;
import com.ifermen.akma.application.mapper.ServiceMapperApplication;
import com.ifermen.akma.application.port.in.service.CreateServiceUseCase;
import com.ifermen.akma.application.port.out.repository.ServiceRepository;
import com.ifermen.akma.domain.model.ServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateServiceUseCaseImpl implements CreateServiceUseCase {

    private ServiceRepository serviceRepository;
    private ServiceMapperApplication serviceMapperApplication;

    @Override
    public ServiceModel execute(CreateServiceCommand createServiceCommand) throws ConfilctException {

        ServiceModel serviceModel = serviceMapperApplication.toServiceModel(createServiceCommand);
        serviceModel.setName(serviceModel.getName().toUpperCase().trim());

        if(this.serviceRepository.findByName(serviceModel.getName()) != null){
            throw new ConfilctException("Service name already exists");
        }

        serviceModel.generateRandomId();

        return serviceRepository.save(serviceModel);
    }
}
