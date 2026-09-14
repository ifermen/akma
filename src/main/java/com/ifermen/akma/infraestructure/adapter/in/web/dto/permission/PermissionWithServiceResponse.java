package com.ifermen.akma.infraestructure.adapter.in.web.dto.permission;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.service.ServiceResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonPropertyOrder(value = {"id", "url", "method", "service", "description", "deletedAt"})
@Data
public class PermissionWithServiceResponse {
    private UUID id;
    private String url;
    private String method;
    private ServiceResponse service;
    private String description;
    private LocalDateTime deletedAt;
}
