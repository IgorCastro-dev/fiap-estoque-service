package com.fiap.estoque_service.gateway;
import com.fiap.estoque_service.controller.json.EstoqueJson;
import com.fiap.estoque_service.domain.Estoque;

import java.util.List;

public interface EstoqueGateway {

    Estoque criar(Estoque cliente);
    Estoque buscaPorSku(String sku);
    String baixar(List<EstoqueJson> estoqueJson);

}
