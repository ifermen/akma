package com.ifermen.akma.infraestructure.jpa.repository;

import com.ifermen.akma.infraestructure.jpa.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceJpaRepository extends JpaRepository<ServiceEntity, Integer> {

    @Query("SELECT s FROM ServiceEntity s WHERE s.name = :name")
    List<ServiceEntity> findByName(@Param("name") String name);

    Optional<ServiceEntity> findById(@Param("id")UUID id);
}
