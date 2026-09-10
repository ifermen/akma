package com.ifermen.akma.application.command.key;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class CreateKeyCommand {
    private UUID serviceId;
    private String name;
    private UUID userId;
    private List<UUID> permissions;
    private LocalDateTime expiresAt;
}
