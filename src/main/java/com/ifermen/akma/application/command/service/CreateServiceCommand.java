package com.ifermen.akma.application.command.service;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateServiceCommand {
    private UUID id;
    private String name;
    private String description;
}
