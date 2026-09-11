package com.ifermen.akma.application.dto.result;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ValidateRequestResult {

    private boolean valid;

    private UUID userId;
}
