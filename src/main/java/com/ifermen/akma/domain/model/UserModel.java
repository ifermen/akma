package com.ifermen.akma.domain.model;

import com.ifermen.akma.infraestructure.jpa.entity.KeyEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class UserModel {

    private UUID id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<KeyEntity> keys;
}
