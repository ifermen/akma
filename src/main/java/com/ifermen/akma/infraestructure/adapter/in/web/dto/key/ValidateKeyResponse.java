package com.ifermen.akma.infraestructure.adapter.in.web.dto.key;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonPropertyOrder({"valid", "userId", "expireAt"})
public class ValidateKeyResponse {

    private boolean valid;

    private UUID userId;
}
