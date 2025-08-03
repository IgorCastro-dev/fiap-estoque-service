package com.fiap.estoque_service.usecase;


import com.fiap.estoque_service.domain.Estoque;
import com.fiap.estoque_service.gateway.EstoqueGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscaEstoquePorCpfSku {
    @Autowired
    private EstoqueGateway estoqueGateway;

    public Estoque buscarEstoquePorSku(String sku){
        return estoqueGateway.buscaPorSku(sku);
    }
}
