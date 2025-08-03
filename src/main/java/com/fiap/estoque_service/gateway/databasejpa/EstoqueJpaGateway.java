package com.fiap.estoque_service.gateway.databasejpa;

import com.fiap.estoque_service.controller.json.EstoqueJson;
import com.fiap.estoque_service.domain.Estoque;
import com.fiap.estoque_service.gateway.EstoqueGateway;
import com.fiap.estoque_service.gateway.databasejpa.entity.EstoqueEntity;
import com.fiap.estoque_service.gateway.databasejpa.repository.EstoqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EstoqueJpaGateway implements EstoqueGateway {
    @Autowired
    EstoqueRepository estoqueRepository;

    @Override
    public Estoque criar(Estoque estoque) {
        EstoqueEntity estoqueEntity = estoqueRepository.findBySku(estoque.getSku())
                .orElseGet(() -> estoqueRepository.save(mapToEntity(estoque)));

        if(estoqueEntity != null){
            estoqueEntity.setQuantidade(estoqueEntity.getQuantidade()+estoque.getQuantidade());
            estoqueRepository.save(estoqueEntity);
        }

        return mapToDto(estoqueEntity);
    }

    @Override
    public Estoque buscaPorSku(String sku) {
        EstoqueEntity estoqueEntity = estoqueRepository.findBySku(sku)
                .orElseThrow(
                        () -> new RuntimeException("Estoque não ENCONTRADO POR ESSE CPF")
                );
        return mapToDto(estoqueEntity);
    }

    @Override
    @Transactional
    public String baixar(List<EstoqueJson> itensEstoque) {

        for (EstoqueJson item : itensEstoque) {

            EstoqueEntity estoqueEntity = estoqueRepository.findBySku(item.getSku())
                    .orElseThrow(() -> new RuntimeException(item.getSku()));


            if (estoqueEntity.getQuantidade() < item.getQuantidade()) {
                throw new RuntimeException("Quantidade de itens não disponível");
            }

            estoqueEntity.setQuantidade(estoqueEntity.getQuantidade() - item.getQuantidade());
            estoqueRepository.save(estoqueEntity);
        }

        return "estoque atualizado";
    }

    private EstoqueEntity mapToEntity(Estoque estoque){
        return EstoqueEntity.builder()
                .sku(estoque.getSku())
                .quantidade(estoque.getQuantidade())
                .build();
    }

    private Estoque mapToDto(EstoqueEntity estoqueEntity){
        return Estoque.builder()
                .quantidade(estoqueEntity.getQuantidade())
                .sku(estoqueEntity.getSku())
                .build();
    }

}
