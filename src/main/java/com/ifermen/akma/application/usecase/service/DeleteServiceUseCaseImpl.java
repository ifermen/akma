package com.ifermen.akma.application.usecase.service;

import com.ifermen.akma.application.port.in.service.DeleteServiceUseCase;
import com.ifermen.akma.application.port.out.repository.ServiceRepository;
import com.ifermen.akma.domain.model.ServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DeleteServiceUseCaseImpl implements DeleteServiceUseCase {

    private ServiceRepository serviceRepository;

    @Override
    public void execute(UUID id){
        //TODO: Verify if has keys associated
        //TODO: Implement Logic Delete
        ServiceModel serviceModel = this.serviceRepository.findById(id);

        this.serviceRepository.delete(serviceModel);
    }
}
