package com.ifermen.akma.application.usecase.service;

import com.ifermen.akma.application.port.in.service.GetServiceUseCase;
import com.ifermen.akma.application.port.out.repository.ServiceRepository;
import com.ifermen.akma.domain.model.ServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GetServiceUseCaseImpl implements GetServiceUseCase {

    private ServiceRepository serviceRepository;

    @Override
    public ServiceModel execute(UUID id){
        ServiceModel serviceModel = serviceRepository.findById(id);

        return serviceModel;
    }
}
