package com.ifermen.akma.infraestructure.adapter.in.web;

import com.ifermen.akma.application.command.CreateServiceCommand;
import com.ifermen.akma.application.port.in.service.CreateService;
import com.ifermen.akma.domain.model.ServiceModel;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.CreateServiceRequest;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.CreateServiceResponse;
import com.ifermen.akma.infraestructure.mapstruct.ServiceMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final CreateService createService;
    private final ServiceMapper serviceMapper;

    public ServiceController(CreateService createService, ServiceMapper serviceMapper) {
        this.createService = createService;
        this.serviceMapper = serviceMapper;
    }

    @PostMapping
    public ResponseEntity<?> createService(@RequestBody CreateServiceRequest request) {

        CreateServiceCommand command = serviceMapper.toCreateServiceRequest(request);
        ServiceModel serviceCreated = createService.execute(command);
        CreateServiceResponse createServiceResponse = serviceMapper.toCreateServiceResponse(serviceCreated);

        URI uri = URI.create("/services/" + createServiceResponse.getId());

        return ResponseEntity.created(uri).body(createServiceResponse);
    }

}
