package com.ifermen.akma.application.usecase;

import com.ifermen.akma.application.command.CreateServiceCommand;
import com.ifermen.akma.application.exception.ConfilctException;
import com.ifermen.akma.application.port.in.service.CreateService;
import com.ifermen.akma.application.port.out.ServiceRepository;
import com.ifermen.akma.domain.model.ServiceModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateServiceImpl implements CreateService {

    private ServiceRepository serviceRepository;

    public CreateServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ServiceModel execute(CreateServiceCommand createServiceCommand) throws ConfilctException {

        createServiceCommand.setName(createServiceCommand.getName().toUpperCase().trim());

        if(this.serviceRepository.findByName(createServiceCommand.getName()) != null){
            throw new ConfilctException("Service name already exists");
        }

        createServiceCommand.setId(UUID.randomUUID());

        return serviceRepository.save(createServiceCommand);
    }
}
