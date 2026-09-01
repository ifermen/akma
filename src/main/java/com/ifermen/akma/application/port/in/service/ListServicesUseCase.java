package com.ifermen.akma.application.port.in.service;

import com.ifermen.akma.domain.model.ServiceModel;

import java.util.List;

public interface ListServicesUseCase {
    List<ServiceModel> execute();
}
