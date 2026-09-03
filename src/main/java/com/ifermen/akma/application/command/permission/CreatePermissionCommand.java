package com.ifermen.akma.application.command.permission;

import lombok.Data;

import java.util.UUID;

@Data
public class CreatePermissionCommand {

    private String target;
    private String privilege;
    private UUID serviceId;
    private String description;
}
