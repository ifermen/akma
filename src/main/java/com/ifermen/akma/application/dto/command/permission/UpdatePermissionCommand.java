package com.ifermen.akma.application.dto.command.permission;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdatePermissionCommand {

    private UUID serviceId;
    private UUID permissionId;
    private String url;
    private String method;
    private String description;
}
