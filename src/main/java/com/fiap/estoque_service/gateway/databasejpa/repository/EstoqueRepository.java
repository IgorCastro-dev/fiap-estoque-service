package com.fiap.estoque_service.gateway.databasejpa.repository;

import com.fiap.estoque_service.domain.Estoque;
import com.fiap.estoque_service.gateway.databasejpa.entity.EstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueEntity,Long> {
    Optional<EstoqueEntity> findBySku(String sku);
}
