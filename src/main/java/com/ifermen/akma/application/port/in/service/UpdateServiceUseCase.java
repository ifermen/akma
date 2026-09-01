package com.ifermen.akma.application.port.in.service;


import com.ifermen.akma.application.command.UpdateServiceCommand;
import com.ifermen.akma.domain.model.ServiceModel;

public interface UpdateServiceUseCase {
    ServiceModel execute(UpdateServiceCommand updateServiceCommand);
}
