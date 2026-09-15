package com.ifermen.akma.application.dto.command.key;

import lombok.Data;

import java.util.UUID;

@Data
public class RevokeKeyCommand {

    private UUID serviceId;
    private String key;
}
