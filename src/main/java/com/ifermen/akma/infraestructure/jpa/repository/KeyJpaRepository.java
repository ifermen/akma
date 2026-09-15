package com.ifermen.akma.infraestructure.jpa.repository;

import com.ifermen.akma.infraestructure.jpa.entity.KeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface KeyJpaRepository extends JpaRepository<KeyEntity, UUID> {

    @Query("SELECT k FROM KeyEntity k WHERE k.keyPrefix = :prefix AND k.revokeAt IS NULL")
    List<KeyEntity> findByPrefix(@Param("prefix") String prefix);

    @Query("SELECT k FROM KeyEntity k WHERE k.service.id = :serviceId AND k.env = :env AND k.userId = userId AND k.revokeAt IS NULL")
    List<KeyEntity> searchByServiceIdEnvUserIdAndRevokeAtNull(
            @Param("serviceId") UUID serviceId,
            @Param("env") String env,
            @Param("userId") UUID userId
    );
}
