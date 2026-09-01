package com.ifermen.akma.application.usecase.service;

import com.ifermen.akma.application.command.UpdateServiceCommand;
import com.ifermen.akma.application.exception.ConfilctException;
import com.ifermen.akma.application.port.in.service.UpdateServiceUseCase;
import com.ifermen.akma.application.port.out.repository.ServiceRepository;
import com.ifermen.akma.domain.model.ServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateServiceUseCaseImpl implements UpdateServiceUseCase {

    private ServiceRepository serviceRepository;

    @Override
    public ServiceModel execute(UpdateServiceCommand updateServiceCommand){
        ServiceModel serviceModel = this.serviceRepository.findById(updateServiceCommand.getId());

        if(updateServiceCommand.getName() != null && !updateServiceCommand.getName().isBlank()){
            if(!serviceModel.getName().equals(updateServiceCommand.getName().trim())){
                if (this.serviceRepository.findByName(updateServiceCommand.getName()) != null){
                    throw new ConfilctException("Service name already exists");
                }
                serviceModel.setName(updateServiceCommand.getName().trim());
            }
        }

        if(updateServiceCommand.getDescription() != null){
            serviceModel.setDescription(updateServiceCommand.getDescription().trim());
        }else{
            serviceModel.setDescription(null);
        }

        return serviceRepository.update(serviceModel);
    }
}
