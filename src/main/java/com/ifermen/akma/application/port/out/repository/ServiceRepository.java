package com.ifermen.akma.application.port.out.repository;

import com.ifermen.akma.domain.model.ServiceModel;

import java.util.List;
import java.util.UUID;

public interface ServiceRepository {
    ServiceModel save(ServiceModel service);

    List<ServiceModel> findAll();

    ServiceModel findById(UUID id);

    ServiceModel findByName(String name);

    ServiceModel update(ServiceModel service);

    void delete(ServiceModel serviceModel);
}
