package com.ifermen.akma.application.port.in.service;

import com.ifermen.akma.domain.model.ServiceModel;

import java.util.UUID;

public interface GetServiceUseCase {
    ServiceModel execute(UUID id);
}
