package com.fiap.estoque_service.controller.json;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueJson {
    @NotBlank
    private String sku;
    @NotNull
    @Positive(message = "A quantidade tem q ser maior que 0")
    private Integer quantidade;
}