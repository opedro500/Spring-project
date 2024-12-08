package com.imd.projeto2.DTO;

import jakarta.validation.constraints.NotNull;

public record ProdutoPedidoDTO(
        @NotNull
        Long id_produto,

        @NotNull
        Long id_pedido
) {
}
