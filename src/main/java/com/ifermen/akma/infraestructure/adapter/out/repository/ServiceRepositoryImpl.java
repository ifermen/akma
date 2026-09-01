package com.ifermen.akma.infraestructure.adapter.out.repository;

import com.ifermen.akma.application.exception.NotFoundException;
import com.ifermen.akma.application.port.out.repository.ServiceRepository;
import com.ifermen.akma.domain.model.ServiceModel;
import com.ifermen.akma.infraestructure.jpa.entity.ServiceEntity;
import com.ifermen.akma.infraestructure.jpa.repository.ServiceJpaRepository;
import com.ifermen.akma.infraestructure.mapstruct.ServiceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Repository
public class ServiceRepositoryImpl implements ServiceRepository {

    private ServiceMapper serviceMapper;
    private ServiceJpaRepository  serviceJpaRepository;

    public ServiceRepositoryImpl(ServiceMapper serviceMapper, ServiceJpaRepository  serviceJpaRepository) {
        this.serviceMapper = serviceMapper;
        this.serviceJpaRepository = serviceJpaRepository;
    }

    @Override
    public ServiceModel save(ServiceModel service) {
        ServiceEntity serviceEntity = serviceMapper.toServiceEntity(service);

        ServiceEntity savedServiceEntity = serviceJpaRepository.save(serviceEntity);

        return serviceMapper.toServiceModel(savedServiceEntity);
    }

    @Override
    public List<ServiceModel> findAll() {
        List<ServiceEntity> serviceEntities = serviceJpaRepository.findAll();
        List<ServiceModel> serviceModels = serviceEntities.stream()
                .map(serviceMapper::toServiceModel)
                .toList();

        return serviceModels;
    }

    @Override
    public ServiceModel findById(UUID id){
        ServiceEntity serviceEntity = serviceJpaRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Service not found")
                );
        return serviceMapper.toServiceModel(serviceEntity);
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

    @Override
    public ServiceModel update(ServiceModel service){
        ServiceEntity serviceEntity = serviceMapper.toServiceEntity(service);

        ServiceEntity savedServiceEntity = serviceJpaRepository.save(serviceEntity);

        return serviceMapper.toServiceModel(savedServiceEntity);
    }

    @Override
    public void delete(ServiceModel serviceModel){
        ServiceEntity serviceEntity = this.serviceMapper.toServiceEntity(serviceModel);
        this.serviceJpaRepository.delete(serviceEntity);
    }
}
