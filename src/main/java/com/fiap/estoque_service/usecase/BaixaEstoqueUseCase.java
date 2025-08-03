package com.fiap.estoque_service.usecase;

import com.fiap.estoque_service.controller.json.EstoqueJson;
import com.fiap.estoque_service.gateway.EstoqueGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaixaEstoqueUseCase {
    @Autowired
    private EstoqueGateway estoqueGateway;

    public String baixaEstoque(List<EstoqueJson> estoqueJson){
        return estoqueGateway.baixar(estoqueJson);
    }
}
