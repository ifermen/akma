package com.ifermen.akma.domain.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ServiceModel {

    private UUID id;

    private String name;

    private String description;

    public void generateRandomId(){
        this.id = UUID.randomUUID();
    }
}
