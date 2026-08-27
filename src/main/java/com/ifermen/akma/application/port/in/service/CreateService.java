package com.ifermen.akma.application.port.in.service;

import com.ifermen.akma.application.command.CreateServiceCommand;
import com.ifermen.akma.domain.model.ServiceModel;

public interface CreateService {

    ServiceModel execute(CreateServiceCommand createServiceCommand);
}
