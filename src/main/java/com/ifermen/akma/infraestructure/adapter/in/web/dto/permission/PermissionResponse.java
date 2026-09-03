package com.ifermen.akma.infraestructure.adapter.in.web.dto.permission;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.ServiceResponse;
import lombok.Data;

import java.util.UUID;

@JsonPropertyOrder(value = {"id", "target", "privilege", "service", "description"})
@Data
public class PermissionResponse {
    private UUID id;
    private String target;
    private String privilege;
    private ServiceResponse service;
    private String description;
}
