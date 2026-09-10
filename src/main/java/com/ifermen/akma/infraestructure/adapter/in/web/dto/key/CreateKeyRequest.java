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
    @TrimmedLength(min = 3, max = 100, message = "name: 'String must be between 3 and 100 chars.'")
    @NotBlank
    private String name;
    @NotNull
    private UUID userId;
    @NotNull
    @NotEmpty
    private List<UUID> permissions;
    //@NotNull
    private LocalDateTime expiresAt;
}
