package com.ifermen.akma.infraestructure.jpa.repository;

import com.ifermen.akma.infraestructure.jpa.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PermissionJpaRepository extends JpaRepository<PermissionEntity, UUID> {

    @Query("SELECT p FROM PermissionEntity p WHERE p.url = :url AND p.method = :method")
    List<PermissionEntity> searchByTargetAndPrivilege(
            @Param("url") String url,
            @Param("method") String method);

    @Query("SELECT p FROM PermissionEntity p WHERE p.service.id = :serviceId")
    List<PermissionEntity> findByServiceId(@Param("serviceId") UUID serviceId);
}
