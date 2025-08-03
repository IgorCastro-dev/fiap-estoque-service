package com.fiap.estoque_service.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@AllArgsConstructor
@Getter
@Builder
public class Estoque {
    private String sku;
    private Integer quantidade;
}