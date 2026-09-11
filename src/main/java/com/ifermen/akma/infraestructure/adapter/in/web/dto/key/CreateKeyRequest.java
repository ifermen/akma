package com.ifermen.akma.infraestructure.adapter.in.web.dto.key;

import com.ifermen.akma.infraestructure.adapter.in.web.validation.TrimmedLength;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class CreateKeyRequest {
    @TrimmedLength(min = 3, max = 3, message = "env: 'String must be 3 chars.'")
    @NotBlank(message = "env: must not be blank")
    private String env;
    @NotNull(message = "userId: must not be null")
    private UUID userId;
    @NotNull(message = "permissions: must not be null")
    @NotEmpty(message = "permissions: must not be empty")
    private List<UUID> permissions;
    //@NotNull
    private LocalDateTime expiresAt;
}
