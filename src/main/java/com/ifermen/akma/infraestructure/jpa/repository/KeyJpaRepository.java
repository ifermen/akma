package com.ifermen.akma.infraestructure.jpa.repository;

import com.ifermen.akma.infraestructure.jpa.entity.KeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KeyJpaRepository extends JpaRepository<KeyEntity, UUID> {
}
