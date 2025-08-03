package com.fiap.estoque_service.controller;

import com.fiap.estoque_service.controller.json.EstoqueJson;
import com.fiap.estoque_service.domain.Estoque;
import com.fiap.estoque_service.usecase.BaixaEstoqueUseCase;
import com.fiap.estoque_service.usecase.BuscaEstoquePorCpfSku;
import com.fiap.estoque_service.usecase.CriarEstoqueUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estoque")
@Tag(name = "Cadastro de Estoque", description = "Um endpoint de cadastro de estoque")
public class EstoqueController {
    @Autowired
    private CriarEstoqueUsecase criarEstoqueUsecase;
    @Autowired
    private BuscaEstoquePorCpfSku buscaEstoquePorCpfSku;
    @Autowired
    private BaixaEstoqueUseCase baixaEstoqueUseCase;

    @Operation(description = "Cria um estoque novo")
    @ApiResponse(responseCode = "201",description = "Estoque cadastrado com sucesso")
    @ApiResponse(responseCode = "500",description = "Erro no servidor ao criar o estoque")
    @PostMapping
    public ResponseEntity<Estoque> criarEstoque(@Valid @RequestBody EstoqueJson estoqueJson){
        return ResponseEntity.status(HttpStatus.CREATED).body(criarEstoqueUsecase.salvaEstoque(mapToDto(estoqueJson)));
    }

    @Operation(description = "Buscar estoque por sku")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque encontrado"),
            @ApiResponse(responseCode = "400", description = "Não existe estoque com esse sku"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor ao buscar o estoque")
    })
    @GetMapping("/{sku}")
    public ResponseEntity<Estoque> buscarPorSku(@Valid @NotNull @PathVariable String sku){
        return ResponseEntity.status(HttpStatus.OK).body(buscaEstoquePorCpfSku.buscarEstoquePorSku(sku));
    }

    @Operation(description = "Baixa o estoque por sku")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque diminuido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não existe estoque com esse cpf"),
            @ApiResponse(responseCode = "400", description = "Quantidade no estoque é menor ou igual que a quantidade passada"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor ao diminuir o estoque")
    })
    @DeleteMapping
    public ResponseEntity<String> baixaEstoque(@Valid @RequestBody List<EstoqueJson> estoqueJson){
        return ResponseEntity.status(HttpStatus.OK).body(baixaEstoqueUseCase.baixaEstoque(estoqueJson));
    }

    private Estoque mapToDto(EstoqueJson estoqueJson){
        return Estoque.builder()
                .sku(estoqueJson.getSku())
                .quantidade(estoqueJson.getQuantidade())
                .build();
    }
}
