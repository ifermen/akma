package com.ifermen.akma.infraestructure.jpa.repository;

import com.ifermen.akma.infraestructure.jpa.entity.KeyPermissionEntity;
import com.ifermen.akma.infraestructure.jpa.entity.KeyPermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyPermissionJpaRepository extends JpaRepository<KeyPermissionEntity, KeyPermissionId> {
}
