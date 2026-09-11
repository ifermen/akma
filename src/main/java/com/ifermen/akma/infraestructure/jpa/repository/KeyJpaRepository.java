package com.ifermen.akma.infraestructure.jpa.repository;

import com.ifermen.akma.infraestructure.jpa.entity.KeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface KeyJpaRepository extends JpaRepository<KeyEntity, UUID> {

    @Query("SELECT k FROM KeyEntity k WHERE k.keyPrefix = :prefix")
    List<KeyEntity> findByPrefix(@Param("prefix") String prefix);
}
