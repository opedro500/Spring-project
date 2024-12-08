package com.imd.projeto2.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProdutosPedidoDTO(
        @NotNull
        List<Long> ids_produtos,

        @NotNull
        Long id_pedido
) {
}
