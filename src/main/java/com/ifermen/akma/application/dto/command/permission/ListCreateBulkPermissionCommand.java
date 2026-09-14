package com.ifermen.akma.application.dto.command.permission;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ListCreateBulkPermissionCommand {

    private UUID serviceId;

    private List<CreateBulkPermission> permissions;
}
