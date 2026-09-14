package com.ifermen.akma.application.dto.command.permission;

import lombok.Data;

@Data
public class CreateBulkPermission {

    private String url;
    private String method;
    private String description;
}
