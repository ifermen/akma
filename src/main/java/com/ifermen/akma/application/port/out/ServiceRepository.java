package com.ifermen.akma.application.port.out;

import com.ifermen.akma.application.command.CreateServiceCommand;
import com.ifermen.akma.domain.model.ServiceModel;

public interface ServiceRepository {
    ServiceModel save(CreateServiceCommand command);

    ServiceModel findByName(String name);
}
