package com.ifermen.akma.infraestructure.adapter.in.web.dto.permission;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.UUID;


@JsonPropertyOrder(value = {"id", "url", "method", "description"})
@Data
public class PermissionResponse {
    private UUID id;
    private String url;
    private String method;
    private String description;
}
