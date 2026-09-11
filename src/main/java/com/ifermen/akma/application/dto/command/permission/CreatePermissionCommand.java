package com.ifermen.akma.application.dto.command.permission;

import lombok.Data;

import java.util.UUID;

@Data
public class CreatePermissionCommand {

    private String url;
    private String method;
    private UUID serviceId;
    private String description;
}
