package com.ifermen.akma.infraestructure.adapter.out.repository;

import com.ifermen.akma.application.command.CreateServiceCommand;
import com.ifermen.akma.application.port.out.ServiceRepository;
import com.ifermen.akma.domain.model.ServiceModel;
import com.ifermen.akma.infraestructure.jpa.entity.ServiceEntity;
import com.ifermen.akma.infraestructure.jpa.repository.ServiceJpaRepository;
import com.ifermen.akma.infraestructure.mapstruct.ServiceMapper;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class ServiceRepositoryImpl implements ServiceRepository {

    private ServiceMapper serviceMapper;
    private ServiceJpaRepository  serviceJpaRepository;

    public ServiceRepositoryImpl(ServiceMapper serviceMapper, ServiceJpaRepository  serviceJpaRepository) {
        this.serviceMapper = serviceMapper;
        this.serviceJpaRepository = serviceJpaRepository;
    }

    @Override
    public ServiceModel save(CreateServiceCommand command) {
        ServiceEntity serviceEntity = serviceMapper.toServiceEntity(command);
        ServiceEntity savedServiceEntity = serviceJpaRepository.save(serviceEntity);
        return serviceMapper.toServiceModel(savedServiceEntity);
    }

    @Override
    public ServiceModel findByName(String name){
        try{
            ServiceEntity serviceEntity = this.serviceJpaRepository.findByName(name).getFirst();
            return serviceMapper.toServiceModel(serviceEntity);
        }catch(NoSuchElementException e){
            return null;
        }
    }
}
