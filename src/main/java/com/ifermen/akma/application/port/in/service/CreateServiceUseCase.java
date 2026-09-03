package com.ifermen.akma.application.port.in.service;

import com.ifermen.akma.application.command.service.CreateServiceCommand;
import com.ifermen.akma.domain.model.ServiceModel;

public interface CreateServiceUseCase {

    ServiceModel execute(CreateServiceCommand createServiceCommand);
}
