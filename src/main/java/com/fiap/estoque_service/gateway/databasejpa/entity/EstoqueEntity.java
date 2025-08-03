package com.fiap.estoque_service.gateway.databasejpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "estoque")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EstoqueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTOQUE")
    private Long id_estoque;

    @Column(name = "SKU", nullable = false, unique = true)
    private String sku;

    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quantidade;
}





