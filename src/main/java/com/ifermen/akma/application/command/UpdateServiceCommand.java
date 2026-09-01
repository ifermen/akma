package com.ifermen.akma.application.command;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateServiceCommand {
    private UUID id;
    private String name;
    private String description;
}
