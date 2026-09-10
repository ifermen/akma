package com.ifermen.akma.infraestructure.adapter.in.web.dto.key;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.UUID;

@Data
@JsonPropertyOrder({"key", "keyPrefix", "userId", "expiresAt", "createdAt"})
public class KeyResponse {
    private String key;
    private String keyPrefix;
    private UUID userId;
    private String expiresAt;
    private String createdAt;
}
