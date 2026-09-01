package com.ifermen.akma.application.usecase.service;

import com.ifermen.akma.application.port.in.service.ListServicesUseCase;
import com.ifermen.akma.application.port.out.repository.ServiceRepository;
import com.ifermen.akma.domain.model.ServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListServicesUseCaseImpl implements ListServicesUseCase {

    private ServiceRepository serviceRepository;

    @Override
    public List<ServiceModel> execute(){
        return serviceRepository.findAll();
    }
}
