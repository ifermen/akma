package com.ifermen.akma.application.usecase.service;

import com.ifermen.akma.application.dto.command.service.UpdateServiceCommand;
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

        updateName(serviceModel,updateServiceCommand.getName());
        updateAcronym(serviceModel,updateServiceCommand.getAcronym());
        updateDescription(serviceModel,updateServiceCommand.getDescription());

        return serviceRepository.update(serviceModel);
    }

    private void updateName(ServiceModel serviceModel, String subject){
        if(subject != null && !subject.isBlank()){

            String name = subject.trim();

            if(!serviceModel.getName().equals(name)){
                if (this.serviceRepository.findByName(name) != null){
                    throw new ConfilctException("Service name already exists");
                }
                serviceModel.setName(name);
            }
        }
    }

    private void updateAcronym(ServiceModel serviceModel, String subject){
        if(subject != null && !subject.isBlank()){

            String acronym = subject.toUpperCase().trim();

            if(!serviceModel.getAcronym().equals(acronym)){
                if (this.serviceRepository.findByAcronym(acronym) != null){
                    throw new ConfilctException("Service acronym already exists");
                }
                serviceModel.setAcronym(acronym);
            }
        }
    }

    private void updateDescription(ServiceModel serviceModel, String subject){
        if(subject != null){
            serviceModel.setDescription(subject.trim());
        }else{
            serviceModel.setDescription(null);
        }
    }
}
