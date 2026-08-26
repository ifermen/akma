package com.ifermen.akma.domain.model;


import lombok.Data;

@Data
public class KeyPermissionModel {

    private PermissionModel permission;

    private KeyModel key;
}
