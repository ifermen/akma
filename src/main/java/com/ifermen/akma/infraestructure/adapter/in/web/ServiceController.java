package com.ifermen.akma.infraestructure.adapter.in.web;

import com.ifermen.akma.application.command.service.CreateServiceCommand;
import com.ifermen.akma.application.command.service.UpdateServiceCommand;
import com.ifermen.akma.application.port.in.service.*;
import com.ifermen.akma.domain.model.ServiceModel;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.CreateServiceRequest;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.ServiceResponse;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.UpdateServiceRequest;
import com.ifermen.akma.infraestructure.apidoc.ServiceControllerDoc;
import com.ifermen.akma.infraestructure.mapstruct.ServiceMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/services")
@AllArgsConstructor
public class ServiceController implements ServiceControllerDoc {

    private final CreateServiceUseCase createServiceUseCase;
    private final ListServicesUseCase listServicesUseCase;
    private final GetServiceUseCase getServiceUseCase;
    private final UpdateServiceUseCase updateServiceUseCase;
    private final DeleteServiceUseCase deleteServiceUseCase;
    private final ServiceMapper serviceMapper;

    @PostMapping
    @Override
    public ResponseEntity<ServiceResponse> createService(
            @Validated @RequestBody CreateServiceRequest request) {

        CreateServiceCommand command = serviceMapper.toCreateServiceCommand(request);
        ServiceModel serviceCreated = this.createServiceUseCase.execute(command);
        ServiceResponse serviceResponse = serviceMapper.toServiceResponse(serviceCreated);

        URI uri = URI.create("/services/" + serviceResponse.getId());

        return ResponseEntity.created(uri).body(serviceResponse);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ServiceResponse>> listServices() {
        List<ServiceModel> services = this.listServicesUseCase.execute();

        List<ServiceResponse> servicesResponse = services.stream()
                .map(serviceMapper::toServiceResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(servicesResponse);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ServiceResponse> getService(
            @PathVariable("id") UUID id) {
        ServiceModel serviceModel = this.getServiceUseCase.execute(id);
        ServiceResponse serviceResponse = this.serviceMapper.toServiceResponse(serviceModel);

        return ResponseEntity.ok(serviceResponse);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<ServiceResponse> updateService(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateServiceRequest updateServiceRequest){

        UpdateServiceCommand command = this.serviceMapper.toUpdateServiceCommand(updateServiceRequest);
        command.setId(id);

        ServiceModel serviceModel = this.updateServiceUseCase.execute(command);
        ServiceResponse serviceResponse = this.serviceMapper.toServiceResponse(serviceModel);

        return ResponseEntity.accepted().body(serviceResponse);
    }

    @DeleteMapping("{id}")
    @Override
    public ResponseEntity<?> deleteService(
            @PathVariable("id") UUID id){
        this.deleteServiceUseCase.execute(id);

        return ResponseEntity.noContent().build();
    }
}
