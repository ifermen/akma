package com.ifermen.akma.application.dto.command.permission;

import lombok.Data;

import java.util.UUID;

@Data
public class DeletePermissionCommand {

    private UUID idService;
    private UUID idPermission;
}
