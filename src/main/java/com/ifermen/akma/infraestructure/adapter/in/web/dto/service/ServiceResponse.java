package com.ifermen.akma.infraestructure.adapter.in.web.dto.service;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.UUID;

@JsonPropertyOrder(value = {"id", "name", "description"})
@Data
public class ServiceResponse {
    private UUID id;
    private String name;
    private String description;
}
